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
class MainViewModel @Inject constructor(val repository: WeatherRepository): ViewModel() {
    val liveDataCurrent: MutableLiveData<Response<WeatherModel>> = MutableLiveData()

    fun getListWeather() = viewModelScope.launch(Dispatchers.IO) {
        liveDataCurrent.postValue(repository.getListWeather())
    }
}