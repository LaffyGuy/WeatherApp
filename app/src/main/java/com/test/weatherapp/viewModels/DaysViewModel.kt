package com.test.weatherapp.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.weatherapp.data.WeatherModel
import com.test.weatherapp.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject


@HiltViewModel
class DaysViewModel @Inject constructor(val repository: WeatherRepository): ViewModel() {
    val listDaysWeather: MutableLiveData<Response<WeatherModel>> = MutableLiveData()

    fun getWeather() = viewModelScope.launch(Dispatchers.IO) {
        listDaysWeather.postValue(repository.getListWeather())
    }
}