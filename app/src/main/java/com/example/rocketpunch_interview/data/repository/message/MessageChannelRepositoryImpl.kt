package com.example.rocketpunch_interview.data.repository.message

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.rocketpunch_interview.data.datasource.DataSource
import com.example.rocketpunch_interview.model.MessageChannel
import com.example.rocketpunch_interview.model.User
import java.util.ArrayList

class MessageChannelRepositoryImpl(private val dataSource: DataSource) : MessageChannelRepository {
    override val messageChannelList = dataSource.messageChannelList
    override val selectedMessageChannel = dataSource.selectedMessageChannel


    override fun setMessageChannel(messageChannel: MessageChannel) {
        dataSource.setMessageChannelList(messageChannel)
    }

    override fun getMessageChannelList() {
        dataSource.getMessageChannelList()
    }

    override fun openMessageChannel(userList: List<User>) {
        dataSource.openMessageChannel(userList)
    }
}