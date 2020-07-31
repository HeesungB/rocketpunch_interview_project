package com.example.rocketpunch_interview.ui.new_message

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.rocketpunch_interview.data.repository.user.UserRepository
import com.example.rocketpunch_interview.model.SingleLiveEvent
import com.example.rocketpunch_interview.model.User
import com.example.rocketpunch_interview.ui.base.BaseViewModel

class NewMessageViewModel(private val userRepository: UserRepository): BaseViewModel() {
    private val _isPageClosed = SingleLiveEvent<Any>()
    private val _searchedUserList = userRepository.searchedList
    private val _searchValue = MutableLiveData<String>()

    val isPageClosed: LiveData<Any> get() = _isPageClosed
    val searchedUserList: LiveData<List<User>> get() = _searchedUserList
    val searchValue: MutableLiveData<String> get() = _searchValue

    fun searchUser(searchValue: String) {
        userRepository.searchUser(searchValue)
    }

    fun onClickBackButton() {
        _isPageClosed.call()
    }

}