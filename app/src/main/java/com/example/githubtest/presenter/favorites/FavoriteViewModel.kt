package com.example.githubtest.presenter.favorites

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubtest.core.entity.Repos
import com.example.githubtest.core.entity.ReposItem
import com.example.githubtest.core.handler.StatusRequest
import com.example.githubtest.core.live_data.SingleLiveEvent
import com.example.githubtest.data.local.database.repos.FavoriteRepository
import com.example.githubtest.data.network.repo.user.UserRepository
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FavoriteViewModel(
    private val repository: FavoriteRepository

) : ViewModel(), StatusRequest {
    val allReposLiveData = SingleLiveEvent<List<ReposItem>>()
    lateinit var repos: Repos


    fun removeFroMFavorite(repo: ReposItem) =
        viewModelScope.launch {
            withContext(Dispatchers.IO) {

                repository.delete(repo)

                getAll()
            }
        }


    fun getAll() =
        viewModelScope.launch {
            allReposLiveData.postValue(repository.getAll())


        }

    override fun onLoading() {
        // show Loading
    }

    override fun onError(exception: Exception) {
        // show error
    }

    override fun onSuccess() {
        // hide loading
    }
}