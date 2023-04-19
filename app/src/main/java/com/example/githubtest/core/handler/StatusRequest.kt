package com.example.githubtest.core.handler

interface StatusRequest {
    fun onLoading()
    fun onError(exception : Exception)
    fun onSuccess()
}
