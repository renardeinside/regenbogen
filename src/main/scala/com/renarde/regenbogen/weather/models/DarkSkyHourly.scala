package com.renarde.regenbogen.weather.models

case class DarkSkyHourly(
                          summary: String,
                          data: List[DarkSkyHourlyData]
                        )
