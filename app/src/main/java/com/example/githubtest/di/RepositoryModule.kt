package com.example.githubtest.di


import com.example.githubtest.data.local.database.repos.FavoriteRepository
import com.example.githubtest.data.local.database.repos.FavoriteRepositoryImpl
import com.example.githubtest.data.network.repo.user.UserRepository
import com.example.githubtest.data.network.repo.user.UserRepositoryImpl
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module

val repositoryModule = module {
    factory<UserRepository> { UserRepositoryImpl(get()) }
    factory<FavoriteRepository> { FavoriteRepositoryImpl(get()) }

}

