package com.example.pokemonapplication

import android.app.Application
import com.google.firebase.FirebaseApp
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(applicationContext)
        startKoin {
            androidContext(this@App)
            modules(listOf(initialModule))
        }
    }
}