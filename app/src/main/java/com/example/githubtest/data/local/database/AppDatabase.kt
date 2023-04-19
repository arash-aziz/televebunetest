package com.example.githubtest.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.githubtest.core.entity.ReposEntity
import com.example.githubtest.data.local.RepoDao

@Database(entities = [ReposEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun repoDao(): RepoDao
}
