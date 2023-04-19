package com.example.githubtest.core.entity



data class ReposItem(
    var fullName: String,
    var id: Int,
    var name: String,
    val avatarUrl: String,
    var `private`: Boolean,
    var isFavorite: Boolean=true,

):CoreReceivedModel{

    fun getEntity():ReposEntity{
       return ReposEntity(fullName, id, name, avatarUrl, private)
    }
}