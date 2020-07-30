package com.example.rocketpunch_interview.repository

import androidx.lifecycle.MutableLiveData
import com.example.rocketpunch_interview.model.MessageChannel

interface MessageChannelRepository {
    val selectedMessageChannel:MutableLiveData<MessageChannel>

    fun setMessageChannel(messageChannel: MessageChannel)
}