package com.example.rocketpunch_interview.model

data class Chat(
    val channelIdx: String,
    val sender: User,
    val receiver: User,
    val content: String,
    val dateTime: String,
    val isRead: Boolean,
    val viewType: RowType
)