package com.example.rocketpunch_interview.ui.chat

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.lifecycle.Observer
import com.example.rocketpunch_interview.R
import com.example.rocketpunch_interview.databinding.ActivityChatBinding
import com.example.rocketpunch_interview.ui.base.BaseActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class ChatActivity: BaseActivity<ActivityChatBinding, ChatViewModel>() {
    override val layoutResourceId: Int get() = R.layout.activity_chat
    override val viewModel: ChatViewModel by viewModel()


    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.isPageClosed.observe(this, Observer {
            finish()
        })

        viewModel.chatList.observe(this, Observer {
            if (it.isNotEmpty()) {
                chatListFocusEnd()
            }
        })

        viewDataBinding.chatList.setOnTouchListener { _, _ ->
            hideKeyboard()
            false
        }
    }

    private fun chatListFocusEnd() {
        viewDataBinding.chatList.smoothScrollToPosition(viewModel.chatList.value!!.size)
    }

    fun Activity.hideKeyboard() {
        hideKeyboard(currentFocus ?: View(this))
    }

    fun Context.hideKeyboard(view: View) {
        val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }


}