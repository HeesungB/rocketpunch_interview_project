package com.example.rocketpunch_interview.ui.message

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.rocketpunch_interview.model.MessageChannel
import com.example.rocketpunch_interview.model.SingleLiveEvent
import com.example.rocketpunch_interview.ui.base.BaseViewModel

class MessageViewModel: BaseViewModel() {
    private val _isNewMessagePageOpen = SingleLiveEvent<Any>()
    private val _messageChannelList = MutableLiveData<ArrayList<MessageChannel>>(arrayListOf())

    val isNewMessagePageOpen: LiveData<Any> get() = _isNewMessagePageOpen
    val messageChannelList:MutableLiveData<ArrayList<MessageChannel>> get() = _messageChannelList

    fun onClickNewMessageChannelButton() {
        _isNewMessagePageOpen.call()
    }

    fun setList(messageChannel: MessageChannel) {
        _messageChannelList.value?.add(messageChannel)
    }
}