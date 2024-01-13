package com.example.coffee.domain.token

import com.example.coffee.core.platform.Either
import com.example.coffee.core.failure.Failure
import com.example.coffee.domain.model.TokenModel

interface TokenRepository {

    suspend fun saveToken(token: TokenModel): Either<Failure, Unit>

}