package com.example.githubtest.di

import androidx.room.Room
import com.example.githubtest.data.local.database.AppDatabase
import org.koin.dsl.module

val databaseModule = module {
    single {
        Room.databaseBuilder(get(), AppDatabase::class.java, "repos-db")
            .build()
    }

    single { get<AppDatabase>().repoDao() }
}