package com.example.pokemonapplication

import android.app.Application
import com.google.firebase.FirebaseApp
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.InternalCoroutinesApi
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    @InternalCoroutinesApi
    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(applicationContext)
        Firebase.database.setPersistenceEnabled(true)
        startKoin {
            androidContext(this@App)
            modules(listOf(initialModule))
        }
    }
}