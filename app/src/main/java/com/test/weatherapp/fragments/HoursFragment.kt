package com.test.weatherapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.test.weatherapp.adapters.HoursAdapter
import com.test.weatherapp.databinding.FragmentHoursBinding
import com.test.weatherapp.viewModels.HoursViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HoursFragment : Fragment() {
     lateinit var bindingClass: FragmentHoursBinding
     lateinit var hoursadapter: HoursAdapter
     lateinit var hoursRecyclerView: RecyclerView
     private val hoursViewModel by viewModels<HoursViewModel>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bindingClass = FragmentHoursBinding.inflate(inflater)
        return bindingClass.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        hoursadapter = HoursAdapter()
        hoursRecyclerView = bindingClass.rvHours
        hoursRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        hoursRecyclerView.adapter = hoursadapter

        hoursViewModel.getListWeather()
        hoursViewModel.liveDataList.observe(viewLifecycleOwner){
             hoursadapter.getWeather(it.body()!!.forecast.forecastday[0].hour)
        }
    }

}