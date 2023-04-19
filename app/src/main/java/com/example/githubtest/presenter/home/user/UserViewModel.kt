package com.example.githubtest.presenter.home.user

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubtest.core.entity.User
import com.example.githubtest.core.handler.StatusRequest
import com.example.githubtest.core.live_data.SingleLiveEvent
import com.example.githubtest.data.network.repo.user.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UserViewModel(
    private val userRepository: UserRepository,

    ) : ViewModel(), StatusRequest {
    val userLiveData = SingleLiveEvent<User>()


    fun getUser() =
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val userData =
                    userRepository.getUser(this@UserViewModel)

                userData?.let {

                    userLiveData.postValue(it)


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