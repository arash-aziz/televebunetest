package com.example.githubtest.core.entity

data class User(
    val avatarUrl: String,
    val email: String?,
    val id: Int,
    val name: String,

    ) : CoreReceivedModel {


}