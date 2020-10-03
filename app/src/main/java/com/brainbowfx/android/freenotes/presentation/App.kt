package com.brainbowfx.android.freenotes.presentation

import android.app.Application
import androidx.appcompat.app.AppCompatActivity
import com.brainbowfx.android.freenotes.di.components.ActivityPerInstanceSubcomponent
import com.brainbowfx.android.freenotes.di.components.ActivitySubComponent
import com.brainbowfx.android.freenotes.di.components.ApplicationComponent
import com.brainbowfx.android.freenotes.di.components.DaggerApplicationComponent
import com.brainbowfx.android.freenotes.di.modules.*
import kotlinx.coroutines.*

class App: Application() {

    companion object {
        lateinit var Instance: App
    }

    lateinit var appComponent: ApplicationComponent
    var activitySubComponent: ActivitySubComponent? = null
    var activityPerInstanceSubComponent: ActivityPerInstanceSubcomponent? = null

    private suspend fun doWork() {
        delay(500)
        Dispatchers.Main
    }

    override fun onCreate() {
        super.onCreate()
        Instance = this

        appComponent = DaggerApplicationComponent.builder()
            .appModule(AppModule(this))
            .databaseModule(DatabaseModule())
            .filesModule(FilesModule())
            .build()
    }

    fun plusActivityPerInstanceSubComponent(): ActivityPerInstanceSubcomponent =
        activityPerInstanceSubComponent ?: appComponent.activityPerInstanceSubComponent()
            .mappersModule(MappersModule())
            .speechRecognitionModule(SpeechRecognitionModule())
            .dateModule(DateModule())
            .build()
            .also { activityPerInstanceSubComponent = it }

    fun plusActivitySubComponent(activity: AppCompatActivity): ActivitySubComponent? =
            activitySubComponent ?: activityPerInstanceSubComponent?.activitySubComponent()
                ?.activityModule(ActivityModule(activity))
                ?.build()
                .also { activitySubComponent = it  }

    fun clearActivitySubComponent() {
        activitySubComponent = null
    }

    fun clearActivityPerInstanceSubComponent() {
        activityPerInstanceSubComponent = null
    }
}