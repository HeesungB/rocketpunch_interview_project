package com.example.rocketpunch_interview.data.repository.channel

import com.example.rocketpunch_interview.data.datasource.DataSource
import com.example.rocketpunch_interview.model.Channel
import com.example.rocketpunch_interview.model.User

class ChannelRepositoryImpl(private val dataSource: DataSource) : ChannelRepository {
    override val channelList = dataSource.channelList
    override val selectedChannel = dataSource.selectedChannel


    override fun setChannel(channel: Channel) {
        dataSource.setChannelList(channel)
    }

    override fun getChannelList() {
        dataSource.getChannelList()
    }

    override fun openChannel(userList: List<User>) {
        dataSource.openChannel(userList)
    }
}