package com.test.weatherapp.repository

import com.test.weatherapp.data.*
import retrofit2.Response

interface WeatherRepository {

    suspend fun getListWeather(): Response<WeatherModel>

}

