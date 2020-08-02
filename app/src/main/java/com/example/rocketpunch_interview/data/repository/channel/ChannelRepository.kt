package com.example.rocketpunch_interview.data.repository.channel

import androidx.lifecycle.LiveData
import com.example.rocketpunch_interview.model.Channel
import com.example.rocketpunch_interview.model.User

interface ChannelRepository {
    val channelList: LiveData<List<Channel>>
    val selectedChannel: LiveData<Channel>

    fun getChannelList()
    fun setChannel(channel: Channel)
    fun openChannel(userList: List<User>)
}