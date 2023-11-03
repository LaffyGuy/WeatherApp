package com.test.weatherapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.test.weatherapp.R
import com.test.weatherapp.data.Hour
import com.test.weatherapp.databinding.ListItemBinding


class HoursAdapter: RecyclerView.Adapter<HoursAdapter.WeatherViewHolder>() {
    var weatherList = emptyList<Hour>()

    class WeatherViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
         var bindingClass = ListItemBinding.bind(itemView)
        fun bind(weather: Hour) = with(bindingClass){
            bindingClass.tvDate.text = weather.time
            bindingClass.tvTemp.text = weather.temp_c.toString()
            bindingClass.tvCondition.text = weather.condition.text
            Glide.with(itemView).load("https:" + weather.condition.icon).into(bindingClass.iv)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return WeatherViewHolder(view)
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        val currentWeather = weatherList[position]
        holder.bind(currentWeather)
    }

    override fun getItemCount(): Int {
        return weatherList.size
    }

    fun getWeather(weatherDay: List<Hour>){
        weatherList = weatherDay
        notifyDataSetChanged()
    }
}