package com.example.rocketpunch_interview.di

import com.example.rocketpunch_interview.data.datasource.DataSource
import com.example.rocketpunch_interview.data.datasource.FireStoreService
import com.example.rocketpunch_interview.data.repository.message.MessageChannelRepository
import com.example.rocketpunch_interview.data.repository.message.MessageChannelRepositoryImpl
import com.google.firebase.firestore.FirebaseFirestore
import org.koin.dsl.module

var dataSourceModule = module {
    single { FirebaseFirestore.getInstance() }

    single<DataSource> { FireStoreService(get()) }
}