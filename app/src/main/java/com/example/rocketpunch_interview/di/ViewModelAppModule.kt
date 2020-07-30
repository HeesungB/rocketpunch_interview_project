package com.example.rocketpunch_interview.di

import com.example.rocketpunch_interview.ui.message.MessageViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

var viewmodelAppModule = module {
    viewModel { MessageViewModel() }
}