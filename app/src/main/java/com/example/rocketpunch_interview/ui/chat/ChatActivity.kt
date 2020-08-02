package com.example.rocketpunch_interview.ui.chat

import android.os.Bundle
import androidx.lifecycle.Observer
import com.example.rocketpunch_interview.R
import com.example.rocketpunch_interview.databinding.ActivityChatBinding
import com.example.rocketpunch_interview.ui.base.BaseActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class ChatActivity: BaseActivity<ActivityChatBinding, ChatViewModel>() {
    override val layoutResourceId: Int get() = R.layout.activity_chat
    override val viewModel: ChatViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.isPageClosed.observe(this, Observer {
            finish()
        })
    }


}