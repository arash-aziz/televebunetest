package com.example.githubtest.data.network.model.response

import com.example.githubtest.core.entity.User
import com.google.gson.annotations.SerializedName
import com.example.githubtest.data.network.base.MappableDTO

data class UserResponse(
    @SerializedName("avatar_url")
    val avatarUrl: String,
    val email: String?,
    val id: Int,
    val login: String,

): MappableDTO<User> {
    override fun map(): User {
        return User(avatarUrl, email, id, login)

    }
}