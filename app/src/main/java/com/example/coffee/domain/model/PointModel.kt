package com.example.coffee.domain.model

import com.example.coffee.data.network.model.PointEntity

data class PointModel(
    val latitude: String,
    val longitude: String
) {

    companion object {
        fun fromEntity(point: PointEntity): PointModel = PointModel(point.latitude, point.longitude)
    }

}