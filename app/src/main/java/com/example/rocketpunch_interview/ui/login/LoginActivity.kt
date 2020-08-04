package com.example.rocketpunch_interview.ui.login

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import com.example.rocketpunch_interview.R
import com.example.rocketpunch_interview.databinding.ActivityLoginBinding
import com.example.rocketpunch_interview.ui.base.BaseActivity
import com.example.rocketpunch_interview.ui.channel.ChannelActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity: BaseActivity<ActivityLoginBinding, LoginViewModel>() {
    override val layoutResourceId: Int get() = R.layout.activity_login
    override val viewModel: LoginViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.myUser.observe(this, Observer {
            if (it != null) {
                startActivity(Intent(this, ChannelActivity::class.java))
                finish()
            }
        })
    }
}