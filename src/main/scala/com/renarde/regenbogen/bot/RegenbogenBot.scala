package com.renarde.regenbogen.bot

import cats.instances.future._
import cats.syntax.functor._
import com.bot4s.telegram.Implicits._
import com.bot4s.telegram.api.RequestHandler
import com.bot4s.telegram.api.declarative.{Commands, Messages}
import com.bot4s.telegram.clients.ScalajHttpClient
import com.bot4s.telegram.future.{Polling, TelegramBot}
import com.bot4s.telegram.models.{KeyboardButton, ReplyKeyboardMarkup}
import com.renarde.regenbogen.weather.DarkSkyClient
import slogging.{LogLevel, LoggerConfig, PrintLoggerFactory}

import scala.concurrent.Future

class RegenbogenBot(val token: String, weatherKey: String) extends TelegramBot
  with Polling
  with Commands[Future]
  with Messages[Future] {

  override val client: RequestHandler[Future] = new ScalajHttpClient(token)
  private val weatherClient: DarkSkyClient = new DarkSkyClient(weatherKey)

  LoggerConfig.factory = PrintLoggerFactory()
  LoggerConfig.level = LogLevel.TRACE


  onCommand("/start") { implicit msg =>
    logger.info("Start request received")
    val welcomeMessage =
      """
        |Welcome to the Weather Bot!
        |Please use command /weather to get your weather forecast.
        |Data is powered by brilliant service by Dark Sky - https://darksky.net/poweredby/
      """.stripMargin
    reply(welcomeMessage).void
  }

  onCommand("/weather") { implicit msg =>
    logger.info("Weather request received")
    val markup = ReplyKeyboardMarkup.singleButton(
      KeyboardButton("Please click here to provide the location", requestLocation = true),
      resizeKeyboard = true
    )
    reply("Please, open the keyboard input and click on the button", replyMarkup = markup).void
  }


  onMessage { implicit msg =>
    using(_.location) { location =>
      val forecast = weatherClient.getForecast(location)
      logger.info(s"forecast string $forecast")
      replyMd(
        forecast
      ).void

    }
  }

}
