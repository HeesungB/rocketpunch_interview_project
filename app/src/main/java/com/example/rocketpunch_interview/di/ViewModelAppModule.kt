package com.example.rocketpunch_interview.di

import com.example.rocketpunch_interview.ui.chat.ChatViewModel
import com.example.rocketpunch_interview.ui.message.MessageViewModel
import com.example.rocketpunch_interview.ui.new_message.NewMessageViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

var viewmodelAppModule = module {
    viewModel { MessageViewModel(get()) }
    viewModel { ChatViewModel(get()) }
    viewModel { NewMessageViewModel(get()) }
}