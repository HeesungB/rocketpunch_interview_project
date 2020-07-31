package com.example.rocketpunch_interview.ui.message

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.rocketpunch_interview.model.MessageChannel
import com.example.rocketpunch_interview.model.SingleLiveEvent
import com.example.rocketpunch_interview.data.repository.message.MessageChannelRepository
import com.example.rocketpunch_interview.ui.base.BaseViewModel

class MessageViewModel(private val messageChannelRepository: MessageChannelRepository): BaseViewModel() {
    private val _isNewMessagePageOpen = SingleLiveEvent<Any>()
    private val _isChatPageOpen = SingleLiveEvent<Any>()
    private val _messageChannelList = MutableLiveData<ArrayList<MessageChannel>>(arrayListOf())

    val isNewMessagePageOpen: LiveData<Any> get() = _isNewMessagePageOpen
    val isChatPageOpen: LiveData<Any> get() = _isChatPageOpen
    val messageChannelList:MutableLiveData<ArrayList<MessageChannel>> get() = _messageChannelList

    fun onClickNewMessageChannelButton() {
        _isNewMessagePageOpen.call()
    }

    fun onClickMessageChannelItem(messageChannel: MessageChannel) {
        messageChannelRepository.setMessageChannel(messageChannel)
        _isChatPageOpen.call()
    }

    fun setList(messageChannel: MessageChannel) {
        _messageChannelList.value?.add(messageChannel)
    }
}