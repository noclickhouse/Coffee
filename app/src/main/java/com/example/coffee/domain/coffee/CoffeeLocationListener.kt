package com.example.coffee.domain.coffee

import android.location.Location
import android.location.LocationListener

class CoffeeLocationListener(
    private val returnLocation: (Double, Double) -> Unit
) : LocationListener {
    override fun onLocationChanged(location: Location) {
        returnLocation.invoke(location.latitude, location.longitude)
    }
}