package com.example.rocketpunch_interview.ui.splash

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.rocketpunch_interview.MessageApplication
import com.example.rocketpunch_interview.model.User
import com.example.rocketpunch_interview.ui.message.MessageActivity
import com.example.rocketpunch_interview.ui.new_message.NewMessageViewModel
import com.google.firebase.firestore.FirebaseFirestore
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

class SplashActivity: AppCompatActivity() {
    val viewModel: SplashViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.onAppStart()

        startActivity(Intent(this, MessageActivity::class.java))
        finish()
    }
}