package com.example.coffee.presentation.coffeeShops

import com.example.coffee.presentation.model.CoffeeShopView
import com.example.coffee.core.platform.UiEvent

sealed class CoffeeShopsNearbyScreenEvent : UiEvent {
    data object ShowEmpty : CoffeeShopsNearbyScreenEvent()
    data class Show(val coffeeShops: List<CoffeeShopView>) : CoffeeShopsNearbyScreenEvent()
}