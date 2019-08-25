package com.renarde.regenbogen

import com.renarde.regenbogen.bot.RegenbogenBot
import slogging._

import scala.concurrent.Await
import scala.concurrent.duration.Duration

object MainApp extends App with LazyLogging {

  LoggerConfig.factory = PrintLoggerFactory()
  LoggerConfig.level = LogLevel.TRACE

  logger.trace("Starting main application")

  val token = sys.env("REGENBOGEN_BOT_TOKEN")
  val weatherKey = sys.env("REGENBOGEN_DS_KEY")

  val bot = new RegenbogenBot(token, weatherKey)
  val eol = bot.run()
  Await.result(eol, Duration.Inf)

  logger.trace("Application shutdown")

}
