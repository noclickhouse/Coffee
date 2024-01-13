package com.example.coffee.presentation.registration

import com.example.coffee.domain.validation.EmailValidator
import com.example.coffee.domain.validation.IdentityValidateable
import com.example.coffee.domain.validation.LengthValidateable
import com.example.coffee.domain.validation.PasswordAgainValidator
import com.example.coffee.domain.validation.PasswordValidator
import com.example.coffee.domain.validation.SimilarityValidateable
import javax.inject.Inject

class RegistrationValidatorImpl @Inject constructor(
    private val emailValidator: EmailValidator,
    private val passwordValidator: PasswordValidator,
    private val passwordAgainValidator: PasswordAgainValidator
) : RegistrationValidator {

    override fun validateEmail(email: String): Boolean =
        emailValidator(SimilarityValidateable(email))

    override fun validatePassword(password: String): Boolean =
        passwordValidator(LengthValidateable(password, 8))

    override fun validatePasswordAgain(password: String, passwordAgain: String): Boolean =
        passwordAgainValidator(IdentityValidateable(password, passwordAgain))

}