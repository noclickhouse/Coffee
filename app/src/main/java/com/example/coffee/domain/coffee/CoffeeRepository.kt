package com.example.coffee.domain.coffee

import com.example.coffee.core.platform.Either
import com.example.coffee.core.failure.Failure

interface CoffeeRepository {

    suspend fun getMenuById(id: Int): Either<Failure, List<CoffeeModel>>

    suspend fun getCoffeeShops(): Either<Failure, List<CoffeeShopModel>>

}