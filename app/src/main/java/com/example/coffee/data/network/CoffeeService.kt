package com.example.coffee.data.network

import com.example.coffee.core.platform.Either
import com.example.coffee.core.failure.Failure
import com.example.coffee.data.CoffeeApi
import com.example.coffee.data.network.model.CoffeeEntity
import com.example.coffee.data.network.model.CoffeeShopEntity
import com.example.coffee.data.network.model.TokenEntityNetwork
import com.example.coffee.data.network.model.UserAuthEntity
import com.example.coffee.data.network.model.UserEntity
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CoffeeService @Inject constructor(retrofit: Retrofit) : CoffeeApi {
    private val coffeeApi by lazy { retrofit.create(CoffeeApi::class.java) }

    override suspend fun register(userEntity: UserEntity): Either<Failure, TokenEntityNetwork> =
        coffeeApi.register(userEntity)

    override suspend fun authorize(userAuthEntity: UserAuthEntity): Either<Failure, TokenEntityNetwork> =
        coffeeApi.authorize(userAuthEntity)

    override suspend fun getMenuById(id: Int): Either<Failure, List<CoffeeEntity>> =
        coffeeApi.getMenuById(id)

    override suspend fun getCoffeeShops(): Either<Failure, List<CoffeeShopEntity>> =
        coffeeApi.getCoffeeShops()

}