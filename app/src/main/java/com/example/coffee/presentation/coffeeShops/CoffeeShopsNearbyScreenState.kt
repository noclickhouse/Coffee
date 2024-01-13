package com.example.coffee.presentation.coffeeShops

import com.example.coffee.presentation.model.CoffeeShopView
import com.example.coffee.core.platform.UiState

sealed interface CoffeeShopsNearbyScreenState : UiState {
    data object Loading : CoffeeShopsNearbyScreenState
    data class ShowingCoffeeShops(val coffeeShops: List<CoffeeShopView>) :
        CoffeeShopsNearbyScreenState
    data object Empty : CoffeeShopsNearbyScreenState
}