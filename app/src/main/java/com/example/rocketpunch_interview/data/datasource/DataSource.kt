package com.example.rocketpunch_interview.data.datasource

import androidx.lifecycle.LiveData
import com.example.rocketpunch_interview.model.Channel
import com.example.rocketpunch_interview.model.MessageChannel
import com.example.rocketpunch_interview.model.User

interface DataSource {
    val myUser: LiveData<User>
    val channelList: LiveData<List<Channel>>
    val searchedList: LiveData<List<User>>
    val selectedChannel: LiveData<Channel>

    fun setMyUser()
    fun createUser()

    fun initUserSearchList()
    fun searchUser(searchValue: String)

    fun getChannelList()
    fun setChannelList(channel: Channel)
    fun openChannel(userList: List<User>)

    fun sendChat(content: String)
}