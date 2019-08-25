package com.renarde.regenbogen.weather

import com.bot4s.telegram.models.Location
import com.renarde.regenbogen.weather.implicits.InstantImplicits._
import com.renarde.regenbogen.weather.models.{DarkSkyHourlyData, DarkSkyResponse}
import com.softwaremill.sttp._
import com.softwaremill.sttp.json4s._
import org.json4s.Serialization
import org.json4s.native.Serialization
import slogging.{LazyLogging, LogLevel, LoggerConfig, PrintLoggerFactory}

class DarkSkyClient(token: String) extends LazyLogging {

  LoggerConfig.factory = PrintLoggerFactory()
  LoggerConfig.level = LogLevel.TRACE

  logger.info("Starting the Dark Sky client")

  val endpoint: String = "api.darksky.net"

  implicit val backend: SttpBackend[Id, Nothing] = HttpURLConnectionBackend()
  implicit val serialization: Serialization = Serialization

  def getForecast(location: Location): String = {
    val parameters = Map(
      "exclude" -> "currently,minutely,daily,alerts,flags",
      "lang" -> "en",
      "units" -> "si"
    )

    val uriWithParameters = Uri(endpoint)
      .scheme("https")
      .path("forecast", token, LocationProvider(location).getParameters)
      .params(parameters)

    logger.info(s"Uri with parameters: ${uriWithParameters.toString}")

    val response = sttp.get(uriWithParameters)
      .parseResponseIf(status => status == 200)
      .response(asJson[DarkSkyResponse])
      .send()

    val parsedResponse = response.code match {
      case StatusCodes.Ok => response.body.right.get
      case _ =>
        logger.error(s"Bad response from the server: ${response.rawErrorBody.left.get}")
        sys.error("Something went wrong!")
    }

    val forecastSummary = prepareForecastString(parsedResponse)

    forecastSummary
  }


  def prepareForecastString(response: DarkSkyResponse): String = {

    val forecastDate = response.hourly.data.head.time.dayRepr
    val maxTemperature = response.hourly.data.maxBy(_.temperature)
    val minTemperature = response.hourly.data.minBy(_.temperature)
    val precipData = response.hourly.data.maxBy(_.precipProbability)
    val precipText: String = preparePrecipText(precipData)

    val forecastString =
      s"""
         |*Weather forecast for $forecastDate *
         |Global summary: ${response.hourly.summary.toLowerCase}
         |
         |*Temperature*
         |Maximum is ${maxTemperature.temperature} at ${maxTemperature.time.hourRepr}
         |Minimum is ${minTemperature.temperature} at ${minTemperature.time.hourRepr}
         |
         |*Rain*
         |$precipText
         |
         |We wish you a pleasant day!
      """.stripMargin


    forecastString
  }

  def preparePrecipText(darkSkyHourlyData: DarkSkyHourlyData): String = {
    logger.info("Inside precip handler")
    if (darkSkyHourlyData.precipProbability > 0) {
      s"Maximal rain probability is ${darkSkyHourlyData.precipProbability * 100}% at ${darkSkyHourlyData.time.hourRepr}"
    }
    else {
      "Rain is not probable in nearest 24 hours. Enjoy your day! ;)"
    }
  }
}