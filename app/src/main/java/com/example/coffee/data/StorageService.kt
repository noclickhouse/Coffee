package com.example.coffee.data

import com.example.coffee.core.platform.Either
import com.example.coffee.core.failure.Failure
import com.example.coffee.data.storage.model.TokenEntityStorage

interface StorageService {

    fun saveToken(token: TokenEntityStorage): Either<Failure, Unit>

    fun getToken(): Either<Failure, TokenEntityStorage>

}