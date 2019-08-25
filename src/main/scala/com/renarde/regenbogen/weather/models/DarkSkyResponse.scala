package com.renarde.regenbogen.weather.models

case class DarkSkyResponse(
                            latitude: Float,
                            longitude: Float,
                            timezone: String,
                            hourly: DarkSkyHourly
                          )



