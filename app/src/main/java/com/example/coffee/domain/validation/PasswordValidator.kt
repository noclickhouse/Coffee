package com.example.coffee.domain.validation

import com.example.coffee.core.validation.Validator
import javax.inject.Inject

class PasswordValidator @Inject constructor() : Validator<LengthValidateable>() {

    override fun validate(value: LengthValidateable): Boolean = value.str.length >= value.length

}