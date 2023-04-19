package com.example.githubtest.core.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "repos")
data class ReposEntity(
    var fullName: String="",
    @PrimaryKey(autoGenerate = false)
    var id: Int=0,
    var name: String="",
    var avatarUrl: String="",
    var `private`: Boolean=false,

)
{


    fun getRepo():ReposItem{
        return ReposItem(fullName, id, name, avatarUrl, private)
    }
}