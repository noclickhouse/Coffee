package com.example.coffee.domain.coffee

import com.example.coffee.data.network.model.CoffeeEntity

data class CoffeeModel(
    val id: Int,
    val name: String,
    val imageURL: String,
    val price: Int
) {

    companion object {
        fun fromEntity(coffee: CoffeeEntity): CoffeeModel = CoffeeModel(
            id = coffee.id,
            name = coffee.name,
            imageURL = coffee.imageURL,
            price = coffee.price
        )
    }

}