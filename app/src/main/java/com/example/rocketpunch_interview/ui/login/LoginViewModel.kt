package com.example.rocketpunch_interview.ui.login

import com.example.rocketpunch_interview.data.repository.user.UserRepository
import com.example.rocketpunch_interview.ui.base.BaseViewModel

class LoginViewModel(
    private val userRepository: UserRepository
): BaseViewModel() {
    val myUser = userRepository.myUser

    fun onLoginButtonClick() {
        userRepository.createUser()
    }
}