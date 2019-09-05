package com.renarde.regenbogen

import com.renarde.regenbogen.bot.RegenbogenBot
import slogging._

import scala.concurrent.Await
import scala.concurrent.duration.Duration

object MainApp extends App with LazyLogging {

  LoggerConfig.factory = PrintLoggerFactory()
  LoggerConfig.level = LogLevel.TRACE

  var bot: RegenbogenBot = _
  val token = sys.env("REGENBOGEN_BOT_TOKEN")
  val weatherKey = sys.env("REGENBOGEN_DS_KEY")
  val applicationWebhookUrl = sys.env("REGENBOGEN_WEBHOOK_URL")
  val applicationPort = sys.env("PORT").toInt

  override def main(args: Array[String]): Unit = {
    super.main(args)

    logger.trace("Starting main application")


    bot = new RegenbogenBot(token, weatherKey, applicationWebhookUrl, applicationPort)
    val eol = bot.run()
    Await.result(eol, Duration.Inf)
  }
}
