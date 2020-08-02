package com.example.rocketpunch_interview.ui.channel

import androidx.lifecycle.LiveData
import com.example.rocketpunch_interview.model.SingleLiveEvent
import com.example.rocketpunch_interview.data.repository.channel.ChannelRepository
import com.example.rocketpunch_interview.data.repository.user.UserRepository
import com.example.rocketpunch_interview.model.Channel
import com.example.rocketpunch_interview.ui.base.BaseViewModel

class ChannelViewModel(
    userRepository: UserRepository,
    private val channelRepository: ChannelRepository
): BaseViewModel() {
    private val _isNewChannelPageOpen = SingleLiveEvent<Any>()
    private val _isChatPageOpen = SingleLiveEvent<Any>()

    val myUser = userRepository.myUser

    val isNewChannelPageOpen: LiveData<Any> get() = _isNewChannelPageOpen
    val isChatPageOpen: LiveData<Any> get() = _isChatPageOpen

    val channelList = channelRepository.channelList

    fun onView() {
        channelRepository.getChannelList()
    }

    fun onClickNewChannelButton() {
        _isNewChannelPageOpen.call()
    }

    fun onClickChannelItem(channel: Channel) {
        channelRepository.setChannel(channel)
        _isChatPageOpen.call()
    }

}