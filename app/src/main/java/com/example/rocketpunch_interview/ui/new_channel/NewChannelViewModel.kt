package com.example.rocketpunch_interview.ui.new_channel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.rocketpunch_interview.data.repository.channel.ChannelRepository
import com.example.rocketpunch_interview.data.repository.user.UserRepository
import com.example.rocketpunch_interview.model.SingleLiveEvent
import com.example.rocketpunch_interview.model.User
import com.example.rocketpunch_interview.ui.base.BaseViewModel

class NewChannelViewModel(
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

    val selectedChannel = channelRepository.selectedChannel


    fun searchUser(searchValue: String) {
        userRepository.searchUser(searchValue)
    }

    fun onClickBackButton() {
        userRepository.initUserSearchList()
        _isPageClosed.call()
    }

    override fun onLoadedView() {
        userRepository.initUserSearchList()
    }

    fun onClickSearchedUser(user: User) {
        channelRepository.openChannel(listOf(user, userRepository.myUser.value!!))
    }

    fun moveToChatActivity() {
        _isChatStart.call()
    }

}