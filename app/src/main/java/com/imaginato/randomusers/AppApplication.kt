package com.imaginato.randomusers

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

/**
 *  AppApplication class
 *  This App contains implementation of Navigation graph, MVVM with clean code architecture,
 *  Hilt - Dependency Injection, Retrofit - for Rest API call, Coroutine, Google Map
 */
@HiltAndroidApp
class AppApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}