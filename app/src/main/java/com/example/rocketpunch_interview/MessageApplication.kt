package com.example.rocketpunch_interview

import androidx.multidex.MultiDexApplication
import com.example.rocketpunch_interview.di.dataSourceModule
import com.example.rocketpunch_interview.di.repositoryModule
import com.example.rocketpunch_interview.di.viewmodelAppModule
import com.example.rocketpunch_interview.util.PreferenceUtil
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MessageApplication: MultiDexApplication() {
    companion object {
        lateinit var preferencesUtil: PreferenceUtil
    }

    override fun onCreate() {
        super.onCreate()

        preferencesUtil = PreferenceUtil(applicationContext)

        startKoin {
            androidContext(this@MessageApplication)
            modules(listOf(
                viewmodelAppModule,
                repositoryModule,
                dataSourceModule
            ))
        }
    }
}