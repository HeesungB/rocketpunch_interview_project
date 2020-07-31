package com.example.rocketpunch_interview.di

import com.example.rocketpunch_interview.data.repository.message.MessageChannelRepository
import com.example.rocketpunch_interview.data.repository.message.MessageChannelRepositoryImpl
import com.example.rocketpunch_interview.data.repository.user.UserRepository
import com.example.rocketpunch_interview.data.repository.user.UserRepositoryImpl
import org.koin.dsl.module

var repositoryModule = module {
    single<UserRepository> { UserRepositoryImpl(get()) }
    single<MessageChannelRepository> { MessageChannelRepositoryImpl() }
}