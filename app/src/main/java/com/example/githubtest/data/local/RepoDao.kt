package com.example.githubtest.data.local

import androidx.room.*
import com.example.githubtest.core.entity.ReposEntity
import com.example.githubtest.core.entity.ReposItem

@Dao
interface RepoDao {
    @Query("SELECT * FROM repos")
    suspend fun getAll(): List<ReposEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(repo: ReposEntity)

    @Delete
    suspend fun delete(repo: ReposEntity)

    @Query("SELECT * FROM repos WHERE id=:id")
    suspend fun getRepoById(id:Int):ReposEntity?
}


