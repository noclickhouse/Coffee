package com.example.coffee.presentation.registration

interface RegistrationValidator {

    fun validateEmail(email: String): Boolean

    fun validatePassword(password: String): Boolean

    fun validatePasswordAgain(password: String, passwordAgain: String): Boolean

}