package com.example.coffee.data.network.coffee

import com.example.coffee.core.platform.Either
import com.example.coffee.core.failure.Failure
import com.example.coffee.data.CoffeeApi
import com.example.coffee.domain.coffee.CoffeeModel
import com.example.coffee.domain.coffee.CoffeeRepository
import com.example.coffee.domain.coffee.CoffeeShopModel
import com.example.coffee.core.platform.map
import javax.inject.Inject

class CoffeeRepositoryImpl @Inject constructor(
    private val networkService: CoffeeApi
) : CoffeeRepository {

    override suspend fun getMenuById(id: Int): Either<Failure, List<CoffeeModel>> =
        networkService.getMenuById(id).map { coffeeList ->
            coffeeList.map { CoffeeModel.fromEntity(it) }
        }

    override suspend fun getCoffeeShops(): Either<Failure, List<CoffeeShopModel>> =
        networkService.getCoffeeShops().map { coffeeShops ->
            coffeeShops.map { CoffeeShopModel.fromEntity(it) }
        }

}