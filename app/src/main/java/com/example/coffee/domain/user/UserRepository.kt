package com.example.coffee.domain.user

import com.example.coffee.core.platform.Either
import com.example.coffee.core.failure.Failure
import com.example.coffee.domain.model.TokenModel
import com.example.coffee.domain.model.UserAuthModel
import com.example.coffee.domain.model.UserModel

interface UserRepository {

    suspend fun register(user: UserModel): Either<Failure, TokenModel>

    suspend fun authorize(user: UserAuthModel): Either<Failure, TokenModel>

}