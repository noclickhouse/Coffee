package com.example.coffee.presentation.coffeeShop

import com.example.coffee.presentation.model.CoffeeView
import com.example.coffee.core.platform.UiEvent

sealed class CoffeeShopScreenEvent : UiEvent {
    data object ShowEmpty : CoffeeShopScreenEvent()

    data class Show(val coffeeList: List<CoffeeView>) : CoffeeShopScreenEvent()

    data class UpdateCoffeeCount(
        val id: Int,
        val count: Int
    ) : CoffeeShopScreenEvent()

    data class SendData(val order: List<CoffeeView>) : CoffeeShopScreenEvent()
}