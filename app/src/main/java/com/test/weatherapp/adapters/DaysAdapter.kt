package com.test.weatherapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.test.weatherapp.R
import com.test.weatherapp.data.Forecastday
import com.test.weatherapp.databinding.ListItemBinding

class DaysAdapter:RecyclerView.Adapter<DaysAdapter.DaysViewHolder>() {
    var listDayWeather = emptyList<Forecastday>()
    private var click: ((Forecastday) -> Unit)? = null
    class DaysViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)  {
      val binding = ListItemBinding.bind(itemView)
        fun bind(item: Forecastday) = with(binding){
           tvTemp.text = item.day.avgtemp_c.toString()
            tvCondition.text = item.day.condition.text
            tvDate.text = item.date
            Glide.with(itemView).load("https:" + item.day.condition.icon).into(iv)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DaysViewHolder {
     val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent , false)
        return DaysViewHolder(view)
    }

    override fun onBindViewHolder(holder: DaysViewHolder, position: Int) {
        val currentWeather = listDayWeather[position]
        holder.bind(currentWeather)

        holder.itemView.setOnClickListener {
            click?.let {callback ->
            callback(currentWeather)
            }
        }

    }

    override fun getItemCount(): Int {
       return listDayWeather.size
    }

    fun onClick(callback: (Forecastday) -> Unit){
        click = callback
    }


    fun getWeather(list: List<Forecastday>){
        listDayWeather = list
        notifyDataSetChanged()
    }

}