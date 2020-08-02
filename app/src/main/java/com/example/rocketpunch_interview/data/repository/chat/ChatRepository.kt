package com.example.rocketpunch_interview.data.repository.chat

interface ChatRepository {
    fun sendChat(content: String)
}