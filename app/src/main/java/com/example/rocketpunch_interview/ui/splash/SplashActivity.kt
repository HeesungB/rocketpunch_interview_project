package com.example.rocketpunch_interview.ui.splash

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.rocketpunch_interview.MessageApplication
import com.example.rocketpunch_interview.model.User
import com.example.rocketpunch_interview.ui.message.MessageActivity
import com.google.firebase.firestore.FirebaseFirestore
import java.util.*

class SplashActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 유저생성을 위한 임시 코드
        if (MessageApplication.preferencesUtil.hasValue("user_id")) {

        } else {
            val uniqueID = UUID.randomUUID().toString()
            MessageApplication.preferencesUtil.setString("user_id", uniqueID)

            val user = User(uniqueID, "배희성", "모바일 개발자", "")

            val db = FirebaseFirestore.getInstance()
            db.collection("users")
                .add(user)
                .addOnSuccessListener { documentReference ->
                    Log.d("zz", "DocumentSnapshot added with ID: ${documentReference.id}")
                }
                .addOnFailureListener { e ->
                    Log.w("zz", "Error adding document", e)
                }
        }

        startActivity(Intent(this, MessageActivity::class.java))
        finish()
    }
}