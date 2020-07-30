package com.example.rocketpunch_interview.ui.message

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.rocketpunch_interview.model.SingleLiveEvent
import com.example.rocketpunch_interview.ui.base.BaseViewModel

class MessageViewModel: BaseViewModel() {
    private val _isNewMessagePageOpen = SingleLiveEvent<Any>()

    val isNewMessagePageOpen : LiveData<Any> get() = _isNewMessagePageOpen

    fun onClickNewMessageChannelButton() {
        _isNewMessagePageOpen.call()
    }

}