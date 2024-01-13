package com.example.coffee.domain.model

import com.example.coffee.data.network.model.UserAuthEntity

data class UserAuthModel(
    val email: String,
    val password: String
)

fun UserAuthModel.toStorageEntity(): UserAuthEntity = UserAuthEntity(email, password)