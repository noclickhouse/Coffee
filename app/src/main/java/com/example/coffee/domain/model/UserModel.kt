package com.example.coffee.domain.model

import com.example.coffee.data.network.model.UserEntity

data class UserModel(
    val email: String,
    val password: String
)

fun UserModel.toStorageEntity(): UserEntity = UserEntity(email, password)