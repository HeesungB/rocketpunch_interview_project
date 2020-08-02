package com.example.rocketpunch_interview.di

import com.example.rocketpunch_interview.ui.chat.ChatViewModel
import com.example.rocketpunch_interview.ui.channel.ChannelViewModel
import com.example.rocketpunch_interview.ui.new_message.NewMessageViewModel
import com.example.rocketpunch_interview.ui.splash.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

var viewModelAppModule = module {
    viewModel { SplashViewModel(get()) }
    viewModel { ChannelViewModel(get(),get()) }
    viewModel { ChatViewModel(get(), get()) }
    viewModel { NewMessageViewModel(get(), get()) }
}