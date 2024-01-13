package com.example.coffee.presentation.authorization

interface AuthorizationValidator {

    fun validateEmail(email: String): Boolean

    fun validatePassword(password: String): Boolean

}