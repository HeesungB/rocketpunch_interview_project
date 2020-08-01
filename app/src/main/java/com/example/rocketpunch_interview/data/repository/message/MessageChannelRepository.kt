package com.example.rocketpunch_interview.data.repository.message

import androidx.lifecycle.LiveData
import com.example.rocketpunch_interview.model.MessageChannel
import com.example.rocketpunch_interview.model.User

interface MessageChannelRepository {
    val messageChannelList: LiveData<List<MessageChannel>>
    val selectedMessageChannel: LiveData<MessageChannel>

    fun getMessageChannelList()
    fun setMessageChannel(messageChannel: MessageChannel)

    fun openMessageChannel(userList: List<User>)
}