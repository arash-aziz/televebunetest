package com.example.githubtest.data.network.repo

import android.util.Log
import com.example.githubtest.core.handler.StatusRequest
import com.google.gson.GsonBuilder
import com.nizek.dabdoob.network.ShowErrorException
import com.example.githubtest.data.network.base.Result
import retrofit2.HttpException
import retrofit2.Response

open class BaseRepository {

    suspend fun <T :  Any> safeApiCall(
        statusRequest: StatusRequest,
        call: suspend () -> Response<T>,
    ): T? {
        return try {
            statusRequest.onLoading()
            val result = call.invoke()

            statusRequest.onSuccess()
            Log.d("+_+_+_+_", result.body().toString())
            result.body()
        } catch (throwable: Throwable) {

            if (throwable is HttpException) {
                val body = convertErrorBody(throwable)
                statusRequest.onError(ShowErrorException("", throwable.code()))
            } else {
                statusRequest.onError(throwable as Exception)
            }
            null
        }
    }

    private fun convertErrorBody(throwable: HttpException): Result<Any>? {
        return try {

            throwable.response()?.errorBody()?.source()?.let {
//
                ModelUtils(GsonBuilder()).toModel<Result<Any>>(it.readUtf8())
            }
        } catch (exception: Exception) {
            null
        }
    }
}