package com.example.rocketpunch_interview.model

data class ChatDto(
    val channelIdx: String = "",
    val sender: User? = null,
    val receiver: User? = null,
    val content: String = "",
    val dateTime: String = "",
    val isRead: Boolean = false
)