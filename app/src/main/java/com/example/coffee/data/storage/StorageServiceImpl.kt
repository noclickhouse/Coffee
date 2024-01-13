package com.example.coffee.data.storage

import android.content.Context
import com.example.coffee.core.extension.empty
import com.example.coffee.core.failure.Failure
import com.example.coffee.core.platform.Either
import com.example.coffee.data.StorageService
import com.example.coffee.data.storage.model.TokenEntityStorage
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class StorageServiceImpl @Inject constructor (
    @ApplicationContext context: Context
) : StorageService {

    companion object {
        private const val SETTINGS = "userAuth"
        private const val TOKEN = "token"
    }

    private val sp = context.getSharedPreferences(SETTINGS, Context.MODE_PRIVATE)

    override fun saveToken(token: TokenEntityStorage): Either<Failure, Unit> = try {
        sp.edit()
            .putString(TOKEN, token.value)
            .apply()
        Either.Right(Unit)
    } catch (e: Exception) {
        Either.Left(Failure.StorageError)
    }

    override fun getToken(): Either<Failure, TokenEntityStorage> = try {
        Either.Right(TokenEntityStorage(sp.getString(TOKEN, String.empty()).orEmpty()))
    } catch (e: Exception) {
        Either.Left(Failure.StorageError)
    }
}