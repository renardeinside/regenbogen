package com.renarde.regenbogen.weather.models

case class DarkSkyHourlyData(
                              time: Long,
                              summary: String,
                              precipIntensity: Float,
                              precipProbability: Float,
                              temperature: Float,
                              apparentTemperature: Float
                            )