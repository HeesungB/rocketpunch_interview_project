package com.example.rocketpunch_interview.data.repository.message

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.rocketpunch_interview.model.MessageChannel
import com.example.rocketpunch_interview.model.User
import java.util.ArrayList

interface MessageChannelRepository {
    val messageChannelList: LiveData<List<MessageChannel>>
    val selectedMessageChannel: LiveData<MessageChannel>

    fun getMessageChannelList()
    fun setMessageChannel(messageChannel: MessageChannel)

    fun openMessageChannel(userList: List<User>)
}