package com.example.rocketpunch_interview.ui.message

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import com.example.rocketpunch_interview.R
import com.example.rocketpunch_interview.databinding.ActivityMessageBinding
import com.example.rocketpunch_interview.model.Chat
import com.example.rocketpunch_interview.model.MessageChannel
import com.example.rocketpunch_interview.model.RowType
import com.example.rocketpunch_interview.model.User
import com.example.rocketpunch_interview.ui.base.BaseActivity
import com.example.rocketpunch_interview.ui.chat.ChatActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class MessageActivity : BaseActivity<ActivityMessageBinding, MessageViewModel>() {
    override val layoutResourceId: Int get() = R.layout.activity_message
    override val viewModel: MessageViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val user1 = User("1","1","1","1")
        val user2 = User("2","2","2","2")
        val chat = Chat(1,user1,user2, "test","test",false, RowType.OTHERCHAT)
        val messageChannel = MessageChannel(
            1,
            arrayListOf(user1, user1),
            chat,
            1
        )

        viewModel.setList(messageChannel)
        viewModel.setList(messageChannel)
        viewModel.setList(messageChannel)
        viewModel.setList(messageChannel)
        viewModel.setList(messageChannel)
        viewModel.setList(messageChannel)
        viewModel.setList(messageChannel)


        viewModel.isNewMessagePageOpen.observe(this, Observer {

        })

        viewModel.isChatPageOpen.observe(this, Observer {
            startActivity(Intent(this, ChatActivity::class.java))
        })

    }
}