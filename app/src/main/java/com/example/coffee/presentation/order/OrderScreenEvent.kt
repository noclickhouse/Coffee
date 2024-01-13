package com.example.coffee.presentation.order

import com.example.coffee.core.platform.UiEvent
import com.example.coffee.presentation.model.CoffeeView

sealed class OrderScreenEvent : UiEvent {
    data object ShowEmpty : OrderScreenEvent()
    data class Show(val order: List<CoffeeView>) : OrderScreenEvent()
    data class UpdateCoffeeCount(
        val id: Int,
        val count: Int
    ) : OrderScreenEvent()
    data object Pay : OrderScreenEvent()
}