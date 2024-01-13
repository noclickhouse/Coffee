package com.example.coffee.domain.validation

import com.example.coffee.core.validation.Validator
import javax.inject.Inject

class PasswordAgainValidator @Inject constructor() : Validator<IdentityValidateable>() {

    override fun validate(value: IdentityValidateable): Boolean = value.required == value.found

}