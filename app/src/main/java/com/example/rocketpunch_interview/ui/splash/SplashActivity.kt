package com.example.rocketpunch_interview.ui.splash

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.rocketpunch_interview.ui.message.MessageActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashActivity: AppCompatActivity() {
    val viewModel: SplashViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.onAppStart()

        startActivity(Intent(this, MessageActivity::class.java))
        finish()
    }
}