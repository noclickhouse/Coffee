package com.example.coffee.presentation.coffeeShop

import androidx.lifecycle.viewModelScope
import com.example.coffee.presentation.model.CoffeeView
import com.example.coffee.core.platform.Either
import com.example.coffee.core.platform.Reducer
import com.example.coffee.core.platform.BaseViewModel
import com.example.coffee.domain.coffee.CoffeeRepository
import com.example.coffee.presentation.model.CoffeeListView
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoffeeShopViewModel @Inject constructor(
    private val coffeeRepository: CoffeeRepository
) : BaseViewModel<CoffeeShopScreenState, CoffeeShopScreenEvent>() {
    private val reducer = CoffeeShopReducer(CoffeeShopScreenState.Loading)

    override val state: StateFlow<CoffeeShopScreenState>
        get() = reducer.state

    fun getMenu(id: Int) {
        viewModelScope.launch {
            when (val coffeeList = coffeeRepository.getMenuById(id)) {
                is Either.Left -> {
                    sendEvent(CoffeeShopScreenEvent.ShowEmpty)
                    openDialog(coffeeList.failure)
                }
                is Either.Right -> {
                    val menu = coffeeList.data.map { CoffeeView.fromModel(it) }
                    sendEvent(CoffeeShopScreenEvent.Show(menu))
                }
            }
        }
    }

    fun updateCount(id: Int, newCount: Int) {
        sendEvent(CoffeeShopScreenEvent.UpdateCoffeeCount(id, newCount))
    }

    fun convertToJson(coffeeList: List<CoffeeView>): String {
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
        val jsonAdapter = moshi.adapter(CoffeeListView::class.java).lenient()
        return jsonAdapter.toJson(CoffeeListView(filter(coffeeList)))
    }

    private fun filter(coffeeList: List<CoffeeView>): List<CoffeeView> =
        coffeeList.filter { it.count > 0 }

    private fun sendEvent(event: CoffeeShopScreenEvent) {
        reducer.sendEvent(event)
    }

    private class CoffeeShopReducer(initialValue: CoffeeShopScreenState) :
        Reducer<CoffeeShopScreenState, CoffeeShopScreenEvent>(initialValue) {
        override fun reduce(oldState: CoffeeShopScreenState, event: CoffeeShopScreenEvent) {
            when (event) {
                CoffeeShopScreenEvent.ShowEmpty -> setState(CoffeeShopScreenState.Empty)
                is CoffeeShopScreenEvent.Show -> setState(
                    CoffeeShopScreenState.CoffeeMenu(event.coffeeList)
                )
                is CoffeeShopScreenEvent.UpdateCoffeeCount -> setState(
                    when (oldState) {
                        is CoffeeShopScreenState.CoffeeMenu -> {
                            val coffeeList = oldState.coffeeList.toMutableList()
                            val coffee = coffeeList.find { it.id == event.id }
                            coffee?.let {
                                val index = coffeeList.indexOf(coffee)
                                coffeeList[index] = it.copy(count = event.count)
                            }
                            CoffeeShopScreenState.CoffeeMenu(coffeeList.toList())
                        }
                        else -> CoffeeShopScreenState.Empty
                    }
                )
                is CoffeeShopScreenEvent.SendData -> setState(
                    CoffeeShopScreenState.SendingData(event.order)
                )
            }
        }
    }
}