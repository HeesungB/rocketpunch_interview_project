package com.example.rocketpunch_interview.ui.splash

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.rocketpunch_interview.ui.channel.ChannelActivity
import com.example.rocketpunch_interview.ui.login.LoginActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashActivity: AppCompatActivity() {
    val viewModel: SplashViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.onLoadedView()

        viewModel.myUser.observe(this, Observer {
            if (it == null) {
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            } else {
                startActivity(Intent(this, ChannelActivity::class.java))
                finish()
            }
        })

    }
}