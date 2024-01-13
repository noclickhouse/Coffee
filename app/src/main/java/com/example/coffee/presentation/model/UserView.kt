package com.example.coffee.presentation.model

import com.example.coffee.domain.model.UserModel
import com.example.coffee.core.extension.empty

data class UserView(
    val email: String,
    val password: String,
    val passwordAgain: String,
    val isEmailNotValid: Boolean,
    val isPasswordNotValid: Boolean,
    val isPasswordAgainNotValid: Boolean
) {

    companion object {
        fun empty(): UserView = UserView(
            email = String.empty(),
            password = String.empty(),
            passwordAgain = String.empty(),
            isEmailNotValid = true,
            isPasswordNotValid = true,
            isPasswordAgainNotValid = false
        )
    }

}

fun UserView.toModel(): UserModel = UserModel(email, password)