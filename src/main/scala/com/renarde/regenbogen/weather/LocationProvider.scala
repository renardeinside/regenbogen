package com.renarde.regenbogen.weather

import com.bot4s.telegram.models.Location

case class LocationProvider(location: Location) {
  val getParameters: String = location.latitude.toString + "," + location.longitude.toString
}
