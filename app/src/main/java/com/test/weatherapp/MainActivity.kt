package com.test.weatherapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.test.weatherapp.databinding.ActivityMainBinding
import com.test.weatherapp.fragments.MainFragment
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var bindingClass: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingClass = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bindingClass.root)

        supportFragmentManager.beginTransaction().replace(R.id.place_holder, MainFragment()).commit()

    }
}