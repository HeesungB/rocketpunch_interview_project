package com.example.rocketpunch_interview.data.datasource

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.rocketpunch_interview.model.User

interface DataSource {
    val searchedList: LiveData<List<User>>

    fun searchUser(searchValue: String)
}