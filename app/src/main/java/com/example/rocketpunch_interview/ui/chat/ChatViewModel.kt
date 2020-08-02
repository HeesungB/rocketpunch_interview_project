package com.example.rocketpunch_interview.ui.chat

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.rocketpunch_interview.model.Chat
import com.example.rocketpunch_interview.model.SingleLiveEvent
import com.example.rocketpunch_interview.data.repository.channel.ChannelRepository
import com.example.rocketpunch_interview.data.repository.chat.ChatRepository
import com.example.rocketpunch_interview.ui.base.BaseViewModel

class ChatViewModel(
    private val chatRepository: ChatRepository,
    private val channelRepository: ChannelRepository
): BaseViewModel() {
    val selectedMessageChannel = channelRepository.selectedChannel
    private val _isPageClosed = SingleLiveEvent<Any>()
    val chatList = chatRepository.chatList

    val isPageClosed: LiveData<Any> get() = _isPageClosed
//    val chatList: LiveData<ArrayList<Chat>> get() = _chatList

    var currentContent = MutableLiveData<String>()

    override fun onViewLoaded() {
        chatRepository.connectChatList()
    }

    fun onClickBackButton() {
        _isPageClosed.call()
    }

    fun sendChat() {
        chatRepository.sendChat(currentContent.value!!)

        currentContent.value = ""
    }
}