package com.example.rocketpunch_interview.data.repository.user

import androidx.lifecycle.LiveData
import com.example.rocketpunch_interview.model.User

interface UserRepository {
    val myUser: LiveData<User>
    val searchedList: LiveData<List<User>>

    fun setMyUser()
    fun createUser()

    fun initUserSearchList()
    fun searchUser(searchValue: String)
}