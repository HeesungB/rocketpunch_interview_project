package com.example.rocketpunch_interview.ui.message

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import com.example.rocketpunch_interview.R
import com.example.rocketpunch_interview.databinding.ActivityMessageBinding
import com.example.rocketpunch_interview.ui.base.BaseActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class MessageActivity : BaseActivity<ActivityMessageBinding, MessageViewModel>() {
    override val layoutResourceId: Int get() = R.layout.activity_message
    override val viewModel: MessageViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.isNewMessagePageOpen.observe(this, Observer {

        })

        viewModel.isChatPageOpen.observe(this, Observer {
            val chatIntent = Intent(this, MessageActivity::class.java)
            chatIntent.putExtra("messageChannelIdx", viewModel.selectedMessageChannel.value?.idx)
            startActivity(chatIntent)
        })

    }
}