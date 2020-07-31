package com.example.rocketpunch_interview.data.repository.user

import androidx.lifecycle.LiveData
import com.example.rocketpunch_interview.model.User

interface UserRepository {
    val searchedList: LiveData<List<User>>

    fun searchUser(searchValue: String)
}