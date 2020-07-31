package com.example.rocketpunch_interview.data.repository.message

import androidx.lifecycle.MutableLiveData
import com.example.rocketpunch_interview.model.MessageChannel

class MessageChannelRepositoryImpl :
    MessageChannelRepository {
    private val _selectedMessageChannel = MutableLiveData<MessageChannel>()

    override val selectedMessageChannel:MutableLiveData<MessageChannel> get() = _selectedMessageChannel

    override fun setMessageChannel(messageChannel: MessageChannel) {
        selectedMessageChannel.value = messageChannel
    }
}