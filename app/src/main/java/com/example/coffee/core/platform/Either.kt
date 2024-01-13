package com.example.coffee.core.platform

sealed class Either<out L, out R> {

    data class Left<out L>(val failure: L) : Either<L, Nothing>()
    data class Right<out R>(val data: R) : Either<Nothing, R>()

    val isLeft get() = this is Left
    val isRight get() = this is Right

    fun <L> left(failure: L) = Left(failure)

    fun <R> right(data: R) = Right(data)
}

fun <L, R, Ro> Either<L, R>.map(
    success: (R) -> Ro
): Either<L, Ro> = when (this) {
    is Either.Left -> Either.Left(this.failure)
    is Either.Right -> Either.Right(success(this.data))
}