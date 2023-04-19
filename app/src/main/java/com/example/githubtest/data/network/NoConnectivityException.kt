package com.nizek.dabdoob.network

import com.example.githubtest.data.network.base.InternetError
import java.io.IOException

class NoConnectivityException : IOException() {
    override fun getLocalizedMessage() : String {
        return InternetError.NoInternet.value
    }
}

class LoginRequiredException : IOException()

class ShowErrorException(private val errorMessage : String , val code : Int) : IOException() {

    override fun getLocalizedMessage() : String {
        return errorMessage
    }
}

