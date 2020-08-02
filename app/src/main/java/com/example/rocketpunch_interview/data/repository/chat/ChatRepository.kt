package com.example.rocketpunch_interview.data.repository.chat

import androidx.lifecycle.LiveData
import com.example.rocketpunch_interview.model.Chat

interface ChatRepository {
    val chatList: LiveData<List<Chat>>

    fun sendChat(content: String)
    fun connectChatList()
}