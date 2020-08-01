package com.example.rocketpunch_interview.model

data class MessageChannel(
    val userList: List<User> = listOf(),
    val currentChat: Chat? = null,
    val unreadChatCount: Long = 0,
    var opponentUser: User? = null
)