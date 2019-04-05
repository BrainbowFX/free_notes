package com.brainbowfx.android.freenotes.presentation

import android.app.Application
import androidx.appcompat.app.AppCompatActivity
import com.brainbowfx.android.freenotes.di.components.ActivityPerInstanceSubcomponent
import com.brainbowfx.android.freenotes.di.components.ActivitySubcomponent
import com.brainbowfx.android.freenotes.di.components.ApplicationComponent
import com.brainbowfx.android.freenotes.di.components.DaggerApplicationComponent
import com.brainbowfx.android.freenotes.di.modules.*

class App: Application() {

    companion object {
        lateinit var Instance: App
    }

    lateinit var appComponent: ApplicationComponent
    var activitySubcomponent: ActivitySubcomponent? = null
    var activityPerInstanceSubcomponent: ActivityPerInstanceSubcomponent? = null

    override fun onCreate() {
        super.onCreate()
        Instance = this
        appComponent = DaggerApplicationComponent.builder()
            .appModule(AppModule(this))
            .databaseModule(DatabaseModule())
            .filesModule(FilesModule())
            .build()
    }

    fun plusActivitySubcomponent(): ActivitySubcomponent =
        activitySubcomponent ?: appComponent.activitySubcomponent()
            .mappersModule(MappersModule())
            .speechRecognitionModule(SpeechRecognitionModule())
            .dateModule(DateModule())
            .build().also { activitySubcomponent = it }

    fun plusActivityPerInstanceSubcomponent(activity: AppCompatActivity): ActivityPerInstanceSubcomponent? =
            activityPerInstanceSubcomponent ?: activitySubcomponent?.activityPerInstanceSubcomponent()
                ?.activityModule(ActivityModule(activity))
                ?.build().also { activityPerInstanceSubcomponent = it  }

    fun clearActivitySubcomponent() {
        activitySubcomponent = null
    }

    fun clearActivityPerInstanceSubcomponent() {
        activityPerInstanceSubcomponent = null
    }
}