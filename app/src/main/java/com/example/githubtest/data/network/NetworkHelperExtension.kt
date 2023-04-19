package com.example.githubtest.data.network

import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build


    @Suppress("DEPRECATION")
    fun ConnectivityManager.isNetworkConnected() : Boolean {
        var result = false
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val networkCapabilities = this.activeNetwork ?: return false
            val activeNetwork =
                this.getNetworkCapabilities(networkCapabilities) ?: return false
            result = when {
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                else -> false
            }
        } else {
            this.run {
                activeNetworkInfo?.run {
                    result = when (type) {
                        ConnectivityManager.TYPE_WIFI -> true
                        ConnectivityManager.TYPE_MOBILE -> true
                        ConnectivityManager.TYPE_ETHERNET -> true
                        else -> false
                    }

                }
            }
        }

        return result
    }


