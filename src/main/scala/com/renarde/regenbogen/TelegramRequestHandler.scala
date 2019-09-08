package com.renarde.regenbogen

import com.amazonaws.services.lambda.runtime.Context
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent
import slogging.{LazyLogging, LogLevel, LoggerConfig, PrintLoggerFactory}

import scala.beans.BeanProperty
import scala.collection.JavaConverters._

object TelegramRequestHandler extends LazyLogging {

  LoggerConfig.factory = PrintLoggerFactory()
  LoggerConfig.level = LogLevel.TRACE

  val token: String = sys.env("REGENBOGEN_BOT_TOKEN")
  val weatherKey: String = sys.env("REGENBOGEN_DS_KEY")
  val applicationWebhookUrl: String = sys.env("REGENBOGEN_WEBHOOK_URL")

  def handle(request: APIGatewayProxyRequestEvent, context: Context): Response = {

    logger.info(s"Request body: ${request.getBody}")
    Response()
  }

  case class Response(@BeanProperty statusCode: Int = 200, @BeanProperty message: String = "Everything is ok!") {
    val defaultHeaders: java.util.Map[String, String] = Map("X-Powered-By" -> "AWS Lambda & Serverless").asJava
  }

}
