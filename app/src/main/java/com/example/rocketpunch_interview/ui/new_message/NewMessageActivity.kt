package com.example.rocketpunch_interview.ui.new_message

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import com.example.rocketpunch_interview.R
import com.example.rocketpunch_interview.databinding.ActivityNewMessageBinding
import com.example.rocketpunch_interview.ui.base.BaseActivity
import com.example.rocketpunch_interview.ui.chat.ChatActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class NewMessageActivity: BaseActivity<ActivityNewMessageBinding, NewMessageViewModel>() {
    override val layoutResourceId: Int get() = R.layout.activity_new_message
    override val viewModel: NewMessageViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.onViewLoaded()

        viewModel.searchValue.observe(this, Observer {
            viewModel.searchUser(it)
        })

        viewModel.isPageClosed.observe(this, Observer {
            finish()
        })

        viewModel.isChatStart.observe(this, Observer {
            startActivity(Intent(this, ChatActivity::class.java))
            finish()
        })
    }
}