package com.test.weatherapp.repository

import com.test.weatherapp.data.WeatherApi
import com.test.weatherapp.data.WeatherModel
import retrofit2.Response
import javax.inject.Inject


class WeatherRealisation @Inject constructor(val api: WeatherApi): WeatherRepository {
    override suspend fun getListWeather(): Response<WeatherModel> {
        return api.getListWeather()
    }
}