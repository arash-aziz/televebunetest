package com.example.githubtest.data.network

import okhttp3.*
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

object ConectivityHandler {


    fun request(request : Request) : Request {
        var newRequest = request

        val requestContentLength : Long? = newRequest.body?.contentLength()
        val headers = GenerateHeaders.getHeaders(requestContentLength ?: 0)
        newRequest = addHeadersToRequest(newRequest , headers)
        return newRequest
    }



    private fun addHeadersToRequest(
        request : Request ,
        networkHeaders : List<NetworkRequestHeader> ,
    ) : Request {
        val requestBuilder = request.newBuilder()
        networkHeaders.forEach {
            requestBuilder.addHeader(it.key , it.value)
        }
        return requestBuilder.build()
    }
    fun isJSONValid(test : String?) : Boolean {
        try {
            test?.let { JSONObject(it) }
        } catch (ex : JSONException) {

            try {
                JSONArray(test)
            } catch (ex1 : JSONException) {
                return false
            }
        }
        return true
    }
}