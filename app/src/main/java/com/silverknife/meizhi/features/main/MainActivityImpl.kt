package com.silverknife.meizhi.features.main

import android.app.Service
import android.location.LocationManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivityImpl : AppCompatActivity(){
    lateinit var locationManager:LocationManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        locationManager = getSystemService(Service.LOCATION_SERVICE) as LocationManager
        print(locationManager.allProviders)
    }
}