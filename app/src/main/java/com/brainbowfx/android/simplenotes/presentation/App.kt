package com.brainbowfx.android.simplenotes.presentation

import android.app.Application
import com.brainbowfx.android.simplenotes.di.components.ApplicationComponent
import com.brainbowfx.android.simplenotes.di.components.DaggerApplicationComponent
import com.brainbowfx.android.simplenotes.di.modules.AppModule
import com.brainbowfx.android.simplenotes.di.modules.DatabaseModule
import com.brainbowfx.android.simplenotes.di.modules.SpeechRecognitionModule

class App: Application() {

    companion object {
        lateinit var Instance: App
    }

    lateinit var appComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        Instance = this
        appComponent = DaggerApplicationComponent.builder()
            .appModule(AppModule(this))
            .databaseModule(DatabaseModule())
            .speechRecognitionModule(SpeechRecognitionModule())
            .build()
    }
}