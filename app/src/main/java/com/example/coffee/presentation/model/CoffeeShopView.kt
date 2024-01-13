package com.example.coffee.presentation.model

import com.example.coffee.presentation.Units

data class CoffeeShopView(
    val id: Int,
    val name: String,
    val distance: Int,
    val distanceUnit: Units
)