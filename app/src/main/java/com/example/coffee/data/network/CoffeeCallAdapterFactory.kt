package com.example.coffee.data.network

import com.example.coffee.core.platform.Either
import com.example.coffee.core.failure.Failure
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Retrofit
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

class CoffeeCallAdapterFactory : CallAdapter.Factory() {
    override fun get(
        returnType: Type,
        annotations: Array<out Annotation>,
        retrofit: Retrofit
    ): CallAdapter<out Any, Call<Either<Failure, *>>>? {
        if (returnType !is ParameterizedType) return null

        val containerType = getParameterUpperBound(0, returnType)

        if (getRawType(containerType) != Either::class.java) return null
        if (containerType !is ParameterizedType) return null

        val errorType = getParameterUpperBound(0, containerType)
        if (getRawType(errorType) != Failure::class.java) return null

        val resultType = getParameterUpperBound(1, containerType)
        return ResultCallAdapter(resultType)
    }
}