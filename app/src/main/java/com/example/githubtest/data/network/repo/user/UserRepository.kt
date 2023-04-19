package com.example.githubtest.data.network.repo.user

import com.example.githubtest.core.entity.Repos
import com.example.githubtest.core.entity.User
import com.example.githubtest.core.handler.StatusRequest
import com.example.githubtest.data.network.repo.BaseRepo


interface UserRepository : BaseRepo {
    suspend fun getUser(statusRequest: StatusRequest):
            User?

    suspend fun getRepos(statusRequest: StatusRequest):
            Repos?

}