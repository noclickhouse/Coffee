package com.example.coffee.data.network

import com.example.coffee.core.platform.Either
import com.example.coffee.core.failure.Failure
import okhttp3.Request
import okhttp3.ResponseBody
import okio.Timeout
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Callback
import retrofit2.Response
import java.lang.reflect.Type

class ResultCallAdapter<R, T>(private val resultType: Type) :
    CallAdapter<R, Call<Either<Failure, T>>> {

        override fun responseType(): Type = resultType

        override fun adapt(call: Call<R>): Call<Either<Failure, T>> = ResultCallWrapper(call)

}

private class ResultCallWrapper<F, T>(
    private val delegate: Call<T>
) : Call<Either<Failure, F>> {

    override fun clone(): Call<Either<Failure, F>> = ResultCallWrapper(delegate.clone())

    override fun execute(): Response<Either<Failure, F>> = wrapResponse(delegate.execute())

    override fun enqueue(callback: Callback<Either<Failure, F>>) = try {
        delegate.enqueue(object : Callback<T> {
            override fun onResponse(
                call: Call<T>,
                response: Response<T>
            ) = callback.onResponse(this@ResultCallWrapper, wrapResponse(response))

            override fun onFailure(call: Call<T>, t: Throwable) =
                callback.onResponse(
                    this@ResultCallWrapper,
                    Response.success(Either.Left(Failure.ServerError(t.message.orEmpty())) as Either<Failure, F>)
                )
        })
    } catch (e: Exception) {
        callback.onResponse(
            this@ResultCallWrapper,
            Response.success(Either.Left(Failure.ServerError()) as Either<Failure, F>)
        )
    }

    override fun isExecuted(): Boolean = delegate.isExecuted

    override fun cancel() = delegate.cancel()

    override fun isCanceled(): Boolean = delegate.isCanceled

    override fun request(): Request = delegate.request()

    override fun timeout(): Timeout = delegate.timeout()

    private fun wrapResponse(response: Response<T>): Response<Either<Failure, F>> = try {
        when (response.isSuccessful) {
            true -> {
                val responseBody = response.body()
                when (responseBody != null) {
                    true -> Response.success(Either.Right(responseBody) as Either<Failure, F>)
                    false -> Response.success(Either.Left(Failure.ServerError()) as Either<Failure, F>)
                }
            }
            false -> {
                val failure = parseErrorBody(response.errorBody())
                when (failure != null) {
                    true -> Response.success(Either.Left(Failure.ServerError(failure)))
                    false -> Response.success(Either.Left(Failure.ServerError()))
                }
            }
        }
    } catch (e: Exception) {
        Response.success(Either.Left(Failure.ServerError()) as Either<Failure, F>)
    }

    private fun parseErrorBody(responseBody: ResponseBody?): String? =
        responseBody?.string()
}