package com.example.coffee.domain.validation

import com.example.coffee.core.validation.Validator
import javax.inject.Inject

class EmailValidator @Inject constructor() : Validator<SimilarityValidateable>() {

    override fun validate(value: SimilarityValidateable): Boolean {
        val emailRegex = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+".toRegex()

        return value.str.matches(emailRegex)
    }

}