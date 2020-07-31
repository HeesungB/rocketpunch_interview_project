package com.example.rocketpunch_interview.data.repository.user


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.rocketpunch_interview.data.datasource.DataSource
import com.example.rocketpunch_interview.model.User

class UserRepositoryImpl(private val dataSource: DataSource): UserRepository {
    override val searchedList: LiveData<List<User>> = dataSource.searchedList

    override fun searchUser(searchValue: String) {
        dataSource.searchUser(searchValue)
    }

}