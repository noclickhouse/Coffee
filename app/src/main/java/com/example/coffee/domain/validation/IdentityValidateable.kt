package com.example.coffee.domain.validation

import com.example.coffee.core.validation.Validateable

data class IdentityValidateable(
    val required: String,
    val found: String
) : Validateable