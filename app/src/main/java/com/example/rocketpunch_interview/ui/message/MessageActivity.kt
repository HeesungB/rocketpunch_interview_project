package com.example.rocketpunch_interview.ui.message

import android.os.Bundle
import androidx.lifecycle.Observer
import com.example.rocketpunch_interview.R
import com.example.rocketpunch_interview.databinding.ActivityMessageBinding
import com.example.rocketpunch_interview.model.Chat
import com.example.rocketpunch_interview.model.MessageChannel
import com.example.rocketpunch_interview.model.User
import com.example.rocketpunch_interview.ui.base.BaseActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class MessageActivity : BaseActivity<ActivityMessageBinding, MessageViewModel>() {
    override val layoutResourceId: Int get() = R.layout.activity_message
    override val viewModel: MessageViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        viewModel.isNewMessagePageOpen.observe(this, Observer {

        })

    }
}