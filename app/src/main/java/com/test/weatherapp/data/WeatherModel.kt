package com.test.weatherapp.data

data class WeatherModel(
    val current: Current,
    val forecast: Forecast,
    val location: Location,
)