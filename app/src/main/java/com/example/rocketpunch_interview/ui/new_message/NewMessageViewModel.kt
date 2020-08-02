package com.example.rocketpunch_interview.ui.new_message

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.rocketpunch_interview.data.repository.channel.ChannelRepository
import com.example.rocketpunch_interview.data.repository.user.UserRepository
import com.example.rocketpunch_interview.model.SingleLiveEvent
import com.example.rocketpunch_interview.model.User
import com.example.rocketpunch_interview.ui.base.BaseViewModel

class NewMessageViewModel(
    private val userRepository: UserRepository,
    private val channelRepository: ChannelRepository
): BaseViewModel() {
    private val _isPageClosed = SingleLiveEvent<Any>()
    private val _searchedUserList = userRepository.searchedList
    private val _searchValue = MutableLiveData<String>()
    private val _isChatStart = SingleLiveEvent<Any>()

    val isPageClosed: LiveData<Any> get() = _isPageClosed
    val searchedUserList: LiveData<List<User>> get() = _searchedUserList
    val searchValue: MutableLiveData<String> get() = _searchValue
    val isChatStart: SingleLiveEvent<Any> get() = _isChatStart

    fun searchUser(searchValue: String) {
        userRepository.searchUser(searchValue)
    }

    fun onClickBackButton() {
        _isPageClosed.call()
    }

    override fun onViewLoaded() {
        userRepository.initUserSearchList()
    }

    fun onClickSearchedUser(user: User) {
        channelRepository.openChannel(listOf(user, userRepository.myUser.value!!))
        _isChatStart.call()
    }

}