package com.example.githubtest.presenter.home.repos

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

class ReposViewModel(
    private val userRepository: UserRepository, private val repository: FavoriteRepository

) : ViewModel(), StatusRequest {
    val reposLiveData = SingleLiveEvent<Repos>()
    val addOrRemoveLiveData = SingleLiveEvent<Boolean>()
    lateinit var repos: Repos


    fun getRepos() =
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val userData =
                    userRepository.getRepos(this@ReposViewModel)

                userData?.let {

                    reposLiveData.postValue(it)


                }
            }
        }

    fun addOrRemoveFromDataBase(repo: ReposItem) =
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                repository.getRepoById(repo.id)?.let {
                    repository.delete(repo)
                    addOrRemoveLiveData.postValue(false)
                } ?: kotlin.run {
                    repository.insert(repo)
                    addOrRemoveLiveData.postValue(true)
                }

            }
        }


    fun getRepoById(repoId: Int, favoriteAct: (Boolean) -> Unit) =
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                repository.getRepoById(repoId)?.let {

                    favoriteAct(true)
                } ?: kotlin.run {

                    favoriteAct(false)
                }
            }
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