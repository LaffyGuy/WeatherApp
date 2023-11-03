package com.test.weatherapp.data

import retrofit2.Response
import retrofit2.http.GET

interface WeatherApi {

    @GET("forecast.json?key=453376e262a2454b916130727231601&q=Kiev&days=2&aqi=no&alerts=no")
    suspend fun getListWeather(): Response<WeatherModel>

}