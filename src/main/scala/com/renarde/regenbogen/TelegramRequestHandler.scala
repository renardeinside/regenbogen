package com.renarde.regenbogen

import java.beans.BeanProperty

import com.amazonaws.services.lambda.runtime.Context
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent
import slogging.{LazyLogging, LogLevel, LoggerConfig, PrintLoggerFactory}

import scala.collection.JavaConverters._

object TelegramRequestHandler extends LazyLogging {

  LoggerConfig.factory = PrintLoggerFactory()
  LoggerConfig.level = LogLevel.TRACE

//  val token: String = sys.env("REGENBOGEN_BOT_TOKEN")
//  val weatherKey: String = sys.env("REGENBOGEN_DS_KEY")
//  val applicationWebhookUrl: String = sys.env("REGENBOGEN_WEBHOOK_URL")

  def handle(request: APIGatewayProxyRequestEvent, context: Context): Response = {

    logger.info(s"Request body: ${request.getBody}")
    Response("It works!")
  }

  case class Response(
                       @BeanProperty body: String,
                       @BeanProperty statusCode: Int = 200)
}
