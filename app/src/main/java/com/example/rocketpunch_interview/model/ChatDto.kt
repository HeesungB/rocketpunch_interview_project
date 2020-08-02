package com.example.rocketpunch_interview.model

data class ChatDto(
    val channelIdx: String,
    val sender: User,
    val receiver: User,
    val content: String,
    val dateTime: String,
    val isRead: Boolean
)