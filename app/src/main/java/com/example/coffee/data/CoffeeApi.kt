package com.example.coffee.data

import com.example.coffee.core.platform.Either
import com.example.coffee.core.failure.Failure
import com.example.coffee.data.network.model.CoffeeEntity
import com.example.coffee.data.network.model.CoffeeShopEntity
import com.example.coffee.data.network.model.TokenEntityNetwork
import com.example.coffee.data.network.model.UserAuthEntity
import com.example.coffee.data.network.model.UserEntity
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface CoffeeApi {

    @POST("auth/register")
    suspend fun register(@Body userEntity: UserEntity): Either<Failure, TokenEntityNetwork>

    @POST("auth/login")
    suspend fun authorize(@Body userAuthEntity: UserAuthEntity): Either<Failure, TokenEntityNetwork>

    @GET("location/{id}/menu")
    suspend fun getMenuById(@Path("id") id: Int): Either<Failure, List<CoffeeEntity>>

    @GET("locations")
    suspend fun getCoffeeShops(): Either<Failure, List<CoffeeShopEntity>>

}