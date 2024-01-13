package com.example.coffee.presentation.authorization

import com.example.coffee.domain.validation.EmailValidator
import com.example.coffee.domain.validation.LengthValidateable
import com.example.coffee.domain.validation.PasswordValidator
import com.example.coffee.domain.validation.SimilarityValidateable
import javax.inject.Inject

class AuthorizationValidatorImpl @Inject constructor(
    private val emailValidator: EmailValidator,
    private val passwordValidator: PasswordValidator
) : AuthorizationValidator {

    override fun validateEmail(email: String): Boolean =
        emailValidator(SimilarityValidateable(email))

    override fun validatePassword(password: String): Boolean =
        passwordValidator(LengthValidateable(password, 8))

}