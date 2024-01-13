package com.example.coffee.presentation.model

import com.example.coffee.domain.coffee.CoffeeModel

data class CoffeeView(
    val id: Int,
    val name: String,
    val icon: String,
    val price: Int,
    val count: Int
) {

    companion object {
        fun fromModel(coffee: CoffeeModel): CoffeeView = CoffeeView(
            id = coffee.id,
            name = coffee.name,
            icon = coffee.imageURL,
            price = coffee.price,
            count = 0
        )
    }

}