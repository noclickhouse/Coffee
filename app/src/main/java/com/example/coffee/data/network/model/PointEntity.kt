package com.example.coffee.data.network.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PointEntity(
    @Json(name = "latitude") val latitude: String,
    @Json(name = "longitude") val longitude: String
)