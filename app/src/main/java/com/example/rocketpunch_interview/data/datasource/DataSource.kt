package com.example.rocketpunch_interview.data.datasource

import androidx.lifecycle.LiveData
import com.example.rocketpunch_interview.model.MessageChannel
import com.example.rocketpunch_interview.model.User

interface DataSource {
    val myUser: LiveData<User>
    val messageChannelList: LiveData<List<MessageChannel>>
    val searchedList: LiveData<List<User>>
    val selectedMessageChannel: LiveData<MessageChannel>

    fun setMyUser()
    fun createUser()

    fun initUserSearchList()
    fun searchUser(searchValue: String)

    fun getMessageChannelList()
    fun setMessageChannelList(messageChannel: MessageChannel)
    fun openMessageChannel(userList: List<User>)
}