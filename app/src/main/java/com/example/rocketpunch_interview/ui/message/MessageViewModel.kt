package com.example.rocketpunch_interview.ui.message

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.rocketpunch_interview.model.MessageChannel
import com.example.rocketpunch_interview.model.SingleLiveEvent
import com.example.rocketpunch_interview.data.repository.message.MessageChannelRepository
import com.example.rocketpunch_interview.data.repository.user.UserRepository
import com.example.rocketpunch_interview.ui.base.BaseViewModel

class MessageViewModel(
    private val userRepository: UserRepository,
    private val messageChannelRepository: MessageChannelRepository
): BaseViewModel() {
    private val _isNewMessagePageOpen = SingleLiveEvent<Any>()
    private val _isChatPageOpen = SingleLiveEvent<Any>()

    val myUser = userRepository.myUser

    val isNewMessagePageOpen: LiveData<Any> get() = _isNewMessagePageOpen
    val isChatPageOpen: LiveData<Any> get() = _isChatPageOpen

    val messageChannelList = messageChannelRepository.messageChannelList

    fun onViewLoaded() {
        messageChannelRepository.getMessageChannelList()
    }

    fun onClickNewMessageChannelButton() {
        _isNewMessagePageOpen.call()
    }

    fun onClickMessageChannelItem(messageChannel: MessageChannel) {
        messageChannelRepository.setMessageChannel(messageChannel)
        _isChatPageOpen.call()
    }

}