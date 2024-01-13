package com.example.coffee.domain.coffee

import com.example.coffee.presentation.Units
import javax.inject.Inject
import kotlin.math.abs
import kotlin.math.cos
import kotlin.math.pow
import kotlin.math.roundToInt
import kotlin.math.sqrt

class DistanceManager @Inject constructor() {

    companion object {
        private const val KM_IN_MERIDIAN = 111.1
        private const val KM_IN_EQUATOR = 111.3
    }

    fun getDistance(pointA: Pair<Double, Double>, pointB: Pair<Double, Double>): Pair<Int, Units> {
        val differenceInDegrees = abs(pointA.first - pointB.first)
        val differenceInKilometers = differenceInDegrees * KM_IN_MERIDIAN
        val cosA = cos(pointA.first)
        val cosB = cos(pointB.first)
        val oneDegreeInAC = cosA * KM_IN_EQUATOR
        val oneDegreeInBD = cosB * KM_IN_EQUATOR
        val degrees = abs(pointA.second - pointB.second)
        val kilometersInAC = oneDegreeInAC * degrees
        val kilometersInBD = oneDegreeInBD * degrees
        val AH = 0.5 * (abs(kilometersInAC - kilometersInBD))
        val DH = sqrt(abs(differenceInKilometers.pow(2) - AH.pow(2)))
        val CH = kilometersInAC - AH
        val CD = sqrt(CH.pow(2) + DH.pow(2))
        val AB = CD

        return if (AB > 0) {
            val distance = AB.roundToInt()
            Pair(distance, Units.Kilometers)
        } else {
            val distance = (AB * 1000).roundToInt()
            Pair(distance, Units.Meters)
        }
    }
}