package com.example.rocketpunch_interview.model

data class Chat(
    val idx: Int,
    val sender: User,
    val receiver: User,
    val content: String,
    val dateTime: String,
    val isRead: Boolean
)