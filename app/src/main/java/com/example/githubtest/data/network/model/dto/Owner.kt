package com.example.githubtest.data.network.model.dto

import com.example.githubtest.core.entity.Owner
import com.google.gson.annotations.SerializedName
import com.example.githubtest.data.network.base.MappableDTO

data class Owner(
    @SerializedName("avatar_url")
    val avatarUrl: String,

): MappableDTO<Owner> {
    override fun map(): Owner {
        return com.example.githubtest.core.entity.Owner(avatarUrl)
    }
}