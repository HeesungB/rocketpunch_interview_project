package com.example.rocketpunch_interview.model

data class Channel(
    val idx: String,
    val userList: List<User>,
    val currentChat: Chat? = null,
    val unreadChatCount: Long = 0,
    var opponentUser: User
)