package com.example.coffee.core.extension

fun String.Companion.empty() = ""

fun String.isEmail(): Boolean {
    val emailRegex = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+".toRegex()
    return this.matches(emailRegex)
}

fun String.isValidPasswordLength(length: Int): Boolean = this.length >= length
