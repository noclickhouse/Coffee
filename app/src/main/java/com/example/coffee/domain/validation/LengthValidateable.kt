package com.example.coffee.domain.validation

import com.example.coffee.core.validation.Validateable

data class LengthValidateable(
    val str: String,
    val length: Int
) : Validateable