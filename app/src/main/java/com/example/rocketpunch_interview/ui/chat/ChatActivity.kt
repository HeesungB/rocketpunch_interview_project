package com.example.rocketpunch_interview.ui.chat

import android.os.Bundle
import androidx.lifecycle.Observer
import com.example.rocketpunch_interview.R
import com.example.rocketpunch_interview.databinding.ActivityChatBinding
import com.example.rocketpunch_interview.model.Chat
import com.example.rocketpunch_interview.model.RowType
import com.example.rocketpunch_interview.model.User
import com.example.rocketpunch_interview.ui.base.BaseActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class ChatActivity: BaseActivity<ActivityChatBinding, ChatViewModel>() {
    override val layoutResourceId: Int get() = R.layout.activity_chat
    override val viewModel: ChatViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val myChat = Chat(
            1,
            User("1","test user 1", "test descripton 1", "test profile"),
            User("2","test user 2", "test descripton 2", "test profile"),
            "my chatmy chatmy chatmy chatmy chatmy chatmy chatmy chatmy chatmy chatmy chatmy chatmy chatmy chatmy chatmy chatmy chatmy chatmy chatmy chatmy chatmy chatmy chatmy chatmy chatmy chatmy chatmy chat",
            "5분 전",
            false,
            RowType.MYCHAT
        )

        val otherChat = Chat(
            2,
            User("2","test user 2", "test descripton 2", "test profile"),
            User("!","test user 1", "test descripton 1", "test profile"),
            "other chat",
            "방금 전",
            false,
            RowType.OTHERCHAT
        )

        viewModel.setList(myChat)
        viewModel.setList(otherChat)
        viewModel.setList(myChat)
        viewModel.setList(otherChat)
        viewModel.setList(myChat)
        viewModel.setList(otherChat)
        viewModel.setList(myChat)
        viewModel.setList(otherChat)
        viewModel.setList(myChat)
        viewModel.setList(otherChat)

        viewDataBinding.chatList.scrollToPosition(-1)

        viewModel.isPageClosed.observe(this, Observer {
            finish()
        })
    }


}