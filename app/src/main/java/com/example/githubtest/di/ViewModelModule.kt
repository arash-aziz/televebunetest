package com.example.githubtest.di

import com.example.githubtest.presenter.favorites.FavoriteViewModel
import com.example.githubtest.presenter.home.repos.ReposViewModel
import com.example.githubtest.presenter.home.user.UserViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { UserViewModel(get()) }
    viewModel { ReposViewModel(get(), get()) }
    viewModel { FavoriteViewModel(get()) }


}