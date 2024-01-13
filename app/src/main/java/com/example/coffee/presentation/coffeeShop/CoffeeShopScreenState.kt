package com.example.coffee.presentation.coffeeShop

import com.example.coffee.presentation.model.CoffeeView
import com.example.coffee.core.platform.UiState

sealed interface CoffeeShopScreenState : UiState {
    data object Loading : CoffeeShopScreenState
    data object Empty : CoffeeShopScreenState
    data class CoffeeMenu(val coffeeList: List<CoffeeView>) : CoffeeShopScreenState
    data class SendingData(val coffeeList: List<CoffeeView>) : CoffeeShopScreenState
}