package com.example.rocketpunch_interview.model

data class ChannelDto(
    val userList: List<User> = listOf(),
    val currentChat: Chat? = null,
    val unreadChatCount: Long = 0
)