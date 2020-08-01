package com.example.rocketpunch_interview.ui.splash

import androidx.lifecycle.ViewModel
import com.example.rocketpunch_interview.data.repository.user.UserRepository

class SplashViewModel(private val userRepository: UserRepository): ViewModel() {
    fun onAppStart() {
        userRepository.setMyUser()
    }
}