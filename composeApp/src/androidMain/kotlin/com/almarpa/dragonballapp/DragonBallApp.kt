package com.almarpa.dragonballapp

import android.app.Application
import com.almarpa.dragonballapp.di.initKoin
import com.almarpa.dragonballapp.di.uiModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger

class DragonBallApp:Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin(extraModules = listOf(uiModule), config = {
            androidLogger()
            androidContext(this@DragonBallApp)
        })
    }
}