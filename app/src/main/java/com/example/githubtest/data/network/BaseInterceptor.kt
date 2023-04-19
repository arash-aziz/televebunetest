package com.example.githubtest.data.network

import android.net.ConnectivityManager
import com.nizek.dabdoob.network.NoConnectivityException
import com.nizek.dabdoob.network.ShowErrorException
import okhttp3.Interceptor
import okhttp3.Response


abstract class BaseInterceptor(
    private val connectivityManager : ConnectivityManager ,
) : Interceptor {


abstract fun otherError(response : Response): ShowErrorException
    override fun intercept(chain : Interceptor.Chain) : Response {

        if (connectivityManager.isNetworkConnected()) {
            synchronized(this) {

                val originalRequest = chain.request()
                val authenticationRequest = ConectivityHandler.request(originalRequest)
                val initialResponse = chain.proceed(authenticationRequest)

                when (initialResponse.code) {


                    200 -> return initialResponse
                    else -> {
//                        try {

                        throw otherError(initialResponse)

                    }
                }

            }
        } else {
            throw NoConnectivityException()
        }
    }



}

