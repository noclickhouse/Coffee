package com.example.coffee.presentation.model

import com.example.coffee.domain.model.UserAuthModel
import com.example.coffee.core.extension.empty

data class AuthView(
    val email: String,
    val password: String,
    val isEmailNotValid: Boolean,
    val isPasswordNotValid: Boolean
) {

    companion object {
        fun empty(): AuthView = AuthView(
            email = String.empty(),
            password = String.empty(),
            isEmailNotValid = true,
            isPasswordNotValid = true
        )
    }

}

fun AuthView.toModel(): UserAuthModel = UserAuthModel(email, password)