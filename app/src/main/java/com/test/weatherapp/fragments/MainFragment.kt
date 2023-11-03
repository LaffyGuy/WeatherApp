package com.test.weatherapp.fragments

import android.Manifest
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayoutMediator
import com.test.weatherapp.adapters.VpAdapter
import com.test.weatherapp.databinding.FragmentMainBinding
import com.test.weatherapp.viewModels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainFragment : Fragment() {
     lateinit var bindingClass: FragmentMainBinding
     private lateinit var pLauncher: ActivityResultLauncher<String>
     private val fList = listOf(HoursFragment(), DaysFragment())
     lateinit var adapter: VpAdapter
     private val tList = listOf("Години","Дні")
     private val mainViewModel by viewModels<MainViewModel>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bindingClass = FragmentMainBinding.inflate(inflater)
        return bindingClass.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        checkPermission()
        init()

        mainViewModel.getListWeather()
        mainViewModel.liveDataCurrent.observe(viewLifecycleOwner){
            val maxMinTemp = "${it.body()!!.forecast.forecastday[0].day.maxtemp_c}Cº/${it.body()!!.forecast.forecastday[0].day.mintemp_c}Cº "
            bindingClass.tvCity.text = it.body()!!.location.name
            bindingClass.tvCondition.text = it.body()!!.current.condition.text
            bindingClass.tvData.text = it.body()!!.current.last_updated
            bindingClass.tvCurrentTemp.text = it.body()!!.current.temp_c.toString()
            bindingClass.tvMinMaxTemp.text = maxMinTemp
            Glide.with(requireActivity()).load("https:"+it.body()!!.current.condition.icon).into(bindingClass.ivWeather)

        }


    }


    private fun init() = with(bindingClass){
        adapter = VpAdapter(activity as FragmentActivity, fList)
        vp.adapter = adapter
        TabLayoutMediator(tbData, vp){
            tab, pos -> tab.text = tList[pos]
        }.attach()
    }

    private fun permissionListener(){
        pLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()){
            Toast.makeText(requireActivity(), "Дозвіл $it", Toast.LENGTH_SHORT).show()
        }
    }

    private fun checkPermission(){
        if(!isPermissionsGranted(Manifest.permission.ACCESS_FINE_LOCATION)){
            permissionListener()
            pLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        }
    }


}