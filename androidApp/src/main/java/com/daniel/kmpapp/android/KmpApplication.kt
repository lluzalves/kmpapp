package com.daniel.kmpapp.android

import android.app.Application
import com.daniel.kmpapp.di.KoinManager
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class KmpApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@KmpApplication)
            androidLogger()
            modules(KoinManager.koinModules())
        }
    }
}