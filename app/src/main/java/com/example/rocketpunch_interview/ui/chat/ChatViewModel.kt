package com.example.rocketpunch_interview.ui.chat

import androidx.lifecycle.LiveData
import com.example.rocketpunch_interview.model.SingleLiveEvent
import com.example.rocketpunch_interview.repository.MessageChannelRepository
import com.example.rocketpunch_interview.ui.base.BaseViewModel

class ChatViewModel(private val messageChannelRepository: MessageChannelRepository): BaseViewModel() {
    val selectedMessageChannel = messageChannelRepository.selectedMessageChannel
    private val _isPageClosed = SingleLiveEvent<Any>()

    val isPageClosed: LiveData<Any> get() = _isPageClosed

    fun onClickBackButton() {
        _isPageClosed.call()
    }
}