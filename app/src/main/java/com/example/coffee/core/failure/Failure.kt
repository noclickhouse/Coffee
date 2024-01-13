package com.example.coffee.core.failure

sealed class Failure {
    data object ConnectionError : Failure()
    data class ServerError(val message: String = "Unexpected server error") : Failure()
    data object StorageError : Failure()

    abstract class FeatureError : Failure() {
        abstract fun getMessage(): String
    }
}