package com.example.coffee.presentation.order

import com.example.coffee.core.platform.BaseViewModel
import com.example.coffee.core.platform.Reducer
import com.example.coffee.presentation.model.CoffeeView
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class OrderViewModel @Inject constructor() : BaseViewModel<OrderScreenState, OrderScreenEvent>(){
    private val reducer = OrderReducer(OrderScreenState.Loading)

    override val state: StateFlow<OrderScreenState>
        get() = reducer.state

    fun show(coffeeList: List<CoffeeView>) {
        sendEvent(OrderScreenEvent.Show(coffeeList))
    }

    fun showEmpty() {
        sendEvent(OrderScreenEvent.ShowEmpty)
    }

    fun pay() {
        sendEvent(OrderScreenEvent.Pay)
    }

    fun updateCoffeeCount(id: Int, newCount: Int) {
        sendEvent(OrderScreenEvent.UpdateCoffeeCount(id, newCount))
    }

    private fun sendEvent(event: OrderScreenEvent) {
        reducer.sendEvent(event)
    }

    private class OrderReducer(initialValue: OrderScreenState) :
        Reducer<OrderScreenState, OrderScreenEvent>(initialValue) {
        override fun reduce(oldState: OrderScreenState, event: OrderScreenEvent) {
            when (event) {
                OrderScreenEvent.ShowEmpty -> setState(OrderScreenState.Empty)
                is OrderScreenEvent.Show -> setState(OrderScreenState.Order(event.order, false))
                is OrderScreenEvent.UpdateCoffeeCount -> setState(
                    when (oldState) {
                        is OrderScreenState.Order -> {
                            val coffeeList = oldState.order.toMutableList()
                            val coffee = coffeeList.find { it.id == event.id }
                            coffee?.let {
                                val index = coffeeList.indexOf(coffee)
                                coffeeList[index] = it.copy(count = event.count)
                            }
                            OrderScreenState.Order(coffeeList, false)
                        }
                        else -> OrderScreenState.Empty
                    }
                )
                OrderScreenEvent.Pay -> setState(
                    when (oldState) {
                        is OrderScreenState.Order -> OrderScreenState.Order(oldState.order, true)
                        else -> OrderScreenState.Empty
                    }
                )
            }
        }
    }
}