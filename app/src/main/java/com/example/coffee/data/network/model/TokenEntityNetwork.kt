package com.example.coffee.data.network.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TokenEntityNetwork(
    @Json(name = "token") val value: String,
    @Json(name = "tokenLifetime") val lifetime: Int
)