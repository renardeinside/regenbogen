package com.renarde.regenbogen.weather.implicits

import java.text.SimpleDateFormat


object InstantImplicits {
  val withHourFormatter: SimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm")
  val dayFormatter: SimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd")

  implicit class InstantRepresentation(val instantValue: Long) {
    val instantWithMilliseconds: Long = instantValue * 1000L
    def hourRepr: String = withHourFormatter.format(instantWithMilliseconds)
    def dayRepr: String = dayFormatter.format(instantWithMilliseconds)
  }

}
