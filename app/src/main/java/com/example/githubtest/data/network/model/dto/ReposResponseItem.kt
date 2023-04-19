package com.example.githubtest.data.network.model.dto

import com.example.githubtest.core.entity.ReposItem
import com.google.gson.annotations.SerializedName
import com.example.githubtest.data.network.base.MappableDTO

data class ReposResponseItem(
    @SerializedName("full_name")
    val fullName: String,
    val id: Int,
    val name: String,
    val owner: Owner,
    val `private`: Boolean,

    ) : MappableDTO<ReposItem> {
    override fun map(): ReposItem {
        return com.example.githubtest.core.entity.ReposItem(
            fullName,
            id,
            name,
            owner.avatarUrl,
            private
        )
    }
}