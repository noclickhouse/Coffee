package com.example.coffee.domain.coffee

import com.example.coffee.data.network.model.CoffeeShopEntity
import com.example.coffee.domain.model.PointModel

data class CoffeeShopModel(
    val id: Int,
    val name: String,
    val point: PointModel
) {

    companion object {
        fun fromEntity(coffeeShop: CoffeeShopEntity): CoffeeShopModel = CoffeeShopModel(
            id = coffeeShop.id,
            name = coffeeShop.name,
            point = PointModel.fromEntity(coffeeShop.point)
        )
    }

}