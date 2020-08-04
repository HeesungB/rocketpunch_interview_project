package com.example.rocketpunch_interview.ui.channel

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import com.example.rocketpunch_interview.R
import com.example.rocketpunch_interview.databinding.ActivityChannelBinding
import com.example.rocketpunch_interview.ui.base.BaseActivity
import com.example.rocketpunch_interview.ui.chat.ChatActivity
import com.example.rocketpunch_interview.ui.login.LoginActivity
import com.example.rocketpunch_interview.ui.new_channel.NewChannelActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class ChannelActivity : BaseActivity<ActivityChannelBinding, ChannelViewModel>() {
    override val layoutResourceId: Int get() = R.layout.activity_channel
    override val viewModel: ChannelViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.isNewChannelPageOpen.observe(this, Observer {
            startActivity(Intent(this, NewChannelActivity::class.java))
        })

        viewModel.isChatPageOpen.observe(this, Observer {
            startActivity(Intent(this, ChatActivity::class.java))
        })
    }

    override fun onResume() {
        super.onResume()

        viewModel.myUser.observe(this, Observer {
            if (it == null) {
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            } else {
                viewModel.onView()
            }

        })
    }
}