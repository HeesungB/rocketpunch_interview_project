package com.example.rocketpunch_interview.data.repository.chat

import com.example.rocketpunch_interview.data.datasource.DataSource

class ChatRepositoryImpl(private val dataSource: DataSource): ChatRepository {
    override fun sendChat(content: String) {
        dataSource.sendChat(content)
    }
}