package com.example.coffee.data.token

import com.example.coffee.core.platform.Either
import com.example.coffee.core.failure.Failure
import com.example.coffee.data.StorageService
import com.example.coffee.domain.model.TokenModel
import com.example.coffee.domain.token.TokenRepository
import com.example.coffee.domain.model.toStorageEntity
import javax.inject.Inject

class TokenRepositoryImpl @Inject constructor(
    private val storageService: StorageService
) : TokenRepository {

    override suspend fun saveToken(token: TokenModel): Either<Failure, Unit> =
        storageService.saveToken(token.toStorageEntity())

}