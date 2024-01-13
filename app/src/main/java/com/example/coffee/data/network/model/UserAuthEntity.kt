package com.example.coffee.data.network.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserAuthEntity(
    @Json(name = "login") val email: String,
    @Json(name = "password") val password: String
)