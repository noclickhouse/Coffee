package com.example.coffee.presentation.order

import com.example.coffee.core.platform.UiState
import com.example.coffee.presentation.model.CoffeeView

sealed interface OrderScreenState : UiState {
    data object Loading : OrderScreenState
    data object Empty : OrderScreenState
    data class Order(val order: List<CoffeeView>, val paid: Boolean) : OrderScreenState
}