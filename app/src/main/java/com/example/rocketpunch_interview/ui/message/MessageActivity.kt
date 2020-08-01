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
import com.example.rocketpunch_interview.ui.new_message.NewMessageActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class MessageActivity : BaseActivity<ActivityMessageBinding, MessageViewModel>() {
    override val layoutResourceId: Int get() = R.layout.activity_message
    override val viewModel: MessageViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.isNewMessagePageOpen.observe(this, Observer {
            startActivity(Intent(this, NewMessageActivity::class.java))
        })

        viewModel.isChatPageOpen.observe(this, Observer {
            startActivity(Intent(this, ChatActivity::class.java))
        })

    }

    override fun onResume() {
        super.onResume()

        viewModel.myUser.observe(this, Observer {
            viewModel.onViewLoaded()
        })
    }
}