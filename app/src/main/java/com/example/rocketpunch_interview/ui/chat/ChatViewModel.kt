package com.example.rocketpunch_interview.ui.chat

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.rocketpunch_interview.model.SingleLiveEvent
import com.example.rocketpunch_interview.data.repository.channel.ChannelRepository
import com.example.rocketpunch_interview.data.repository.chat.ChatRepository
import com.example.rocketpunch_interview.ui.base.BaseViewModel

class ChatViewModel(
    private val chatRepository: ChatRepository,
    private val channelRepository: ChannelRepository
): BaseViewModel() {
    private val _isPageClosed = SingleLiveEvent<Any>()
    val isPageClosed: LiveData<Any> get() = _isPageClosed

    val chatList = chatRepository.chatList
    val selectedChannel = channelRepository.selectedChannel

    var currentContent = MutableLiveData<String>()

    override fun onViewLoaded() {
        chatRepository.connectChatList()
    }

    fun onClickBackButton() {
        channelRepository.initSelectedChannel()
        chatRepository.initChatList()
        _isPageClosed.call()
    }

    fun sendChat() {
        chatRepository.sendChat(currentContent.value!!)

        currentContent.value = ""
    }
}