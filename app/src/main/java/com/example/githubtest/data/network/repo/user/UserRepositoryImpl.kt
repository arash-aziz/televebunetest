package com.example.githubtest.data.network.repo.user

import com.example.githubtest.core.entity.Repos
import com.example.githubtest.core.entity.User
import com.example.githubtest.core.handler.StatusRequest
import com.example.githubtest.data.network.repo.BaseRepository
import com.example.githubtest.data.network.service.UserService

class UserRepositoryImpl(
    private val apiService: UserService,

    ) : BaseRepository(), UserRepository {


    override suspend fun getUser(statusRequest: StatusRequest): User? {
        return safeApiCall(
            statusRequest = statusRequest,
            call = { apiService.getUser() }
        )?.map()
    }

    override suspend fun getRepos(statusRequest: StatusRequest): Repos? {
        return safeApiCall(
            statusRequest = statusRequest,
            call = { apiService.getRepos() }
        )?.map()
    }
}

