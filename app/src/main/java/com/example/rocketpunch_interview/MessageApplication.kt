package com.example.rocketpunch_interview

import android.app.Application
import com.example.rocketpunch_interview.di.repositoryModule
import com.example.rocketpunch_interview.di.viewmodelAppModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MessageApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MessageApplication)
            modules(listOf(
                viewmodelAppModule,
                repositoryModule
            ))
        }
    }
}