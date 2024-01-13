package com.example.coffee.presentation.coffeeShops

import androidx.lifecycle.viewModelScope
import com.example.coffee.core.platform.BaseViewModel
import com.example.coffee.domain.coffee.CoffeeRepository
import com.example.coffee.presentation.model.CoffeeShopView
import com.example.coffee.domain.coffee.DistanceManager
import com.example.coffee.core.platform.Either
import com.example.coffee.core.platform.Reducer
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoffeeShopsNearbyViewModel @Inject constructor(
    private val coffeeRepository: CoffeeRepository,
    private val distanceManager: DistanceManager
) : BaseViewModel<CoffeeShopsNearbyScreenState, CoffeeShopsNearbyScreenEvent>() {
    private val reducer = CoffeeShopsNearbyReducer(CoffeeShopsNearbyScreenState.Loading)

    override val state: StateFlow<CoffeeShopsNearbyScreenState>
        get() = reducer.state

    fun getCoffeeShops(coordinates: Pair<Double, Double>) {
        viewModelScope.launch {
            when (val coffeeShops = coffeeRepository.getCoffeeShops()) {
                is Either.Left -> {
                    sendEvent(CoffeeShopsNearbyScreenEvent.ShowEmpty)
                    openDialog(coffeeShops.failure)
                }
                is Either.Right -> {
                    val shops = coffeeShops.data.map {
                        val pointBLat = it.point.latitude.toDouble()
                        val pointBLong = it.point.longitude.toDouble()
                        val pointB = Pair(pointBLat, pointBLong)
                        val distance = distanceManager.getDistance(coordinates, pointB)
                        CoffeeShopView(
                            id = it.id,
                            name = it.name,
                            distance = distance.first,
                            distanceUnit = distance.second
                        )
                    }
                    sendEvent(CoffeeShopsNearbyScreenEvent.Show(shops))
                }
            }
        }
    }

    private fun sendEvent(event: CoffeeShopsNearbyScreenEvent) {
        reducer.sendEvent(event)
    }

    private class CoffeeShopsNearbyReducer(initialValue: CoffeeShopsNearbyScreenState) :
        Reducer<CoffeeShopsNearbyScreenState, CoffeeShopsNearbyScreenEvent>(initialValue) {
        override fun reduce(
            oldState: CoffeeShopsNearbyScreenState,
            event: CoffeeShopsNearbyScreenEvent
        ) {
            when (event) {
                is CoffeeShopsNearbyScreenEvent.Show -> setState(
                    CoffeeShopsNearbyScreenState.ShowingCoffeeShops(event.coffeeShops)
                )
                CoffeeShopsNearbyScreenEvent.ShowEmpty -> setState(
                    CoffeeShopsNearbyScreenState.Empty
                )
            }
        }
    }
}