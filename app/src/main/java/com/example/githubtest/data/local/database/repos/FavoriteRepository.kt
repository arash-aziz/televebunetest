package com.example.githubtest.data.local.database.repos

import com.example.githubtest.core.entity.ReposItem
import com.example.githubtest.data.network.repo.BaseRepo

interface FavoriteRepository:BaseRepo {


    suspend fun getAll(): List<ReposItem>

    suspend fun insert(repo: ReposItem)

    suspend fun delete(repo: ReposItem)

    suspend fun getRepoById(id:Int): ReposItem?

}