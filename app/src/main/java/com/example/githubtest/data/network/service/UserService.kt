package com.example.githubtest.data.network.service

import com.example.githubtest.core.entity.User
import com.example.githubtest.data.network.base.Result
import com.example.githubtest.data.network.model.response.ReposResponse
import com.example.githubtest.data.network.model.response.UserResponse

import retrofit2.Response
import retrofit2.http.*

interface UserService {


    @GET("user")
    suspend fun getUser() : Response<UserResponse>

    @GET("/user/repos")
    suspend fun getRepos() : Response<ReposResponse>





}
