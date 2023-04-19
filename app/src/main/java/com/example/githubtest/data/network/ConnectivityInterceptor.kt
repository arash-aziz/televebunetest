package com.example.githubtest.data.network

import android.net.ConnectivityManager
import com.google.gson.Gson
import com.google.gson.JsonElement
import com.google.gson.JsonParser
import com.nizek.dabdoob.network.ShowErrorException

import com.example.githubtest.data.network.base.Result
import okhttp3.Response
import java.nio.charset.Charset

class ConnectivityInterceptor(
    connectivityManager : ConnectivityManager ,
) : BaseInterceptor(connectivityManager) {


    override fun otherError(response : Response) : ShowErrorException {
        val body = response.body
        val buffer = body?.source()
        val charset = body?.contentType()?.charset(Charset.forName("UTF-8"))
        val json = charset?.let { buffer?.readString(it) }
        var obj : JsonElement? = null
        if (ConectivityHandler.isJSONValid(json)) {

            obj = JsonParser().parse(json)
        }
        var errorMessage = "server error"

        return ShowErrorException(errorMessage , response.code)

    }

}