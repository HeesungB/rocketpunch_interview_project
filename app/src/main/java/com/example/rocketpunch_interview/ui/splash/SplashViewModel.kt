package com.example.rocketpunch_interview.ui.splash

import com.example.rocketpunch_interview.data.repository.user.UserRepository
import com.example.rocketpunch_interview.ui.base.BaseViewModel

class SplashViewModel(private val userRepository: UserRepository): BaseViewModel() {
    val myUser = userRepository.myUser

    override fun onLoadedView() {
        userRepository.setMyUser()
    }
}