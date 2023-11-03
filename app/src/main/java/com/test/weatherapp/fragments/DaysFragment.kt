package com.test.weatherapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.test.weatherapp.adapters.DaysAdapter
import com.test.weatherapp.databinding.FragmentDaysBinding
import com.test.weatherapp.viewModels.DaysViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DaysFragment : Fragment() {
    lateinit var bindingClass: FragmentDaysBinding
    lateinit var daysAdapter: DaysAdapter
    private val daysViewModel by viewModels<DaysViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bindingClass = FragmentDaysBinding.inflate(inflater)
        return bindingClass.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        daysAdapter = DaysAdapter()
        bindingClass.rvDays.adapter = daysAdapter
        bindingClass.rvDays.layoutManager = LinearLayoutManager(requireContext())

        daysViewModel.getWeather()
        daysViewModel.listDaysWeather.observe(viewLifecycleOwner){
            daysAdapter.getWeather(it.body()!!.forecast.forecastday)
        }

       daysAdapter.onClick {
          val action = DaysFragmentDirections.actionDaysFragmentToMainFragment(it)
           findNavController().navigate(action)
       }

    }


}