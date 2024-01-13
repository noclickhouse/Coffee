package com.example.coffee.core.validation

abstract class Validator<T : Validateable> {

    abstract fun validate(value: T): Boolean

    operator fun invoke(value: T): Boolean = validate(value)

}