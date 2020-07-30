package com.example.rocketpunch_interview.di

import com.example.rocketpunch_interview.repository.MessageChannelRepository
import com.example.rocketpunch_interview.repository.MessageChannelRepositoryImpl
import org.koin.dsl.module

var repositoryModule = module {
    single<MessageChannelRepository> { MessageChannelRepositoryImpl() }
}