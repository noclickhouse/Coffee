package com.example.coffee.domain.model

import com.example.coffee.data.network.model.TokenEntityNetwork
import com.example.coffee.data.storage.model.TokenEntityStorage

data class TokenModel(
    val value: String,
    val lifetime: Int
) {

    companion object {
        fun fromNetworkEntity(token: TokenEntityNetwork): TokenModel = TokenModel(token.value, token.lifetime)
    }

}

fun TokenModel.toStorageEntity(): TokenEntityStorage = TokenEntityStorage(value)