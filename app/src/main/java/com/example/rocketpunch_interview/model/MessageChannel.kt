package com.example.rocketpunch_interview.model

data class MessageChannel(
    val idx: Int,
    val userList: ArrayList<User>,
    val currentChat: Chat,
    val unreadChatCount: Int
) {
    fun getOpponentUser(): User {
        return userList[0]
    }
}