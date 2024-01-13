package com.example.coffee.data.user

import com.example.coffee.data.CoffeeApi
import com.example.coffee.core.platform.Either
import com.example.coffee.core.failure.Failure
import com.example.coffee.domain.model.TokenModel
import com.example.coffee.domain.model.UserAuthModel
import com.example.coffee.domain.model.UserModel
import com.example.coffee.domain.model.toStorageEntity
import com.example.coffee.domain.user.UserRepository
import com.example.coffee.core.platform.map
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val networkService: CoffeeApi
) : UserRepository {

    override suspend fun register(user: UserModel): Either<Failure, TokenModel> =
        networkService.register(user.toStorageEntity()).map { TokenModel.fromNetworkEntity(it) }

    override suspend fun authorize(user: UserAuthModel): Either<Failure, TokenModel> =
        networkService.authorize(user.toStorageEntity()).map { TokenModel.fromNetworkEntity(it) }

}