package com.example.rocketpunch_interview.ui.chat

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.rocketpunch_interview.model.Chat
import com.example.rocketpunch_interview.model.SingleLiveEvent
import com.example.rocketpunch_interview.data.repository.channel.ChannelRepository
import com.example.rocketpunch_interview.ui.base.BaseViewModel

class ChatViewModel(channelRepository: ChannelRepository): BaseViewModel() {
    val selectedMessageChannel = channelRepository.selectedChannel
    private val _isPageClosed = SingleLiveEvent<Any>()
    private val _chatList = MutableLiveData<ArrayList<Chat>>(arrayListOf())

    val isPageClosed: LiveData<Any> get() = _isPageClosed

    val chatList: LiveData<ArrayList<Chat>> get() = _chatList

    fun onClickBackButton() {
        _isPageClosed.call()
    }
}