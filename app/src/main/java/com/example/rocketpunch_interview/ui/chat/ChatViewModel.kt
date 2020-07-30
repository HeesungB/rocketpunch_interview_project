package com.example.rocketpunch_interview.ui.chat

import com.example.rocketpunch_interview.repository.MessageChannelRepository
import com.example.rocketpunch_interview.ui.base.BaseViewModel

class ChatViewModel(private val messageChannelRepository: MessageChannelRepository): BaseViewModel() {
    val selectedMessageChannel = messageChannelRepository.selectedMessageChannel

}