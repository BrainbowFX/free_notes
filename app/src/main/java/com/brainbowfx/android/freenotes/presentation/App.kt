package com.brainbowfx.android.freenotes.presentation

import android.app.Activity
import android.app.Application
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.brainbowfx.android.freenotes.PREF_DARK_THEME
import com.brainbowfx.android.freenotes.di.components.PresenterSubComponent
import com.brainbowfx.android.freenotes.di.components.ActivitySubComponent
import com.brainbowfx.android.freenotes.di.components.ApplicationComponent
import com.brainbowfx.android.freenotes.di.components.DaggerApplicationComponent
import com.brainbowfx.android.freenotes.di.modules.*
import javax.inject.Inject

class App : Application() {

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    companion object {
        lateinit var Instance: App
    }

    private lateinit var appComponent: ApplicationComponent

    var activitySubComponent: ActivitySubComponent? = null
        private set

    var presenterSubComponent: PresenterSubComponent? = null
        private set

    private val activityLifecycleCallbacks = object : ActivityLifecycleCallbacks {
        private var isSaveInstanceStateCalled: Boolean = false

        override fun onActivityCreated(activity: Activity, bundle: Bundle?) {
            plusPresenterSubComponent()
            plusActivitySubComponent(activity as AppCompatActivity)
        }

        override fun onActivityStarted(activity: Activity) = Unit

        override fun onActivityResumed(activity: Activity) = Unit

        override fun onActivityPaused(activity: Activity) = Unit

        override fun onActivityStopped(activity: Activity) = Unit

        override fun onActivitySaveInstanceState(activity: Activity, bundle: Bundle) {
            isSaveInstanceStateCalled = true
        }

        override fun onActivityDestroyed(activity: Activity) {
            clearActivitySubComponent()
            if (!isSaveInstanceStateCalled) clearActivityPerInstanceSubComponent()
        }

    }

    override fun onCreate() {
        super.onCreate()
        Instance = this

        registerActivityLifecycleCallbacks(activityLifecycleCallbacks)

        appComponent = DaggerApplicationComponent.builder()
            .appModule(AppModule(this))
            .databaseModule(DatabaseModule())
            .filesModule(FilesModule())
            .build().also {
                it.inject(this)
            }

        val theme = sharedPreferences.getInt(PREF_DARK_THEME, AppCompatDelegate.MODE_NIGHT_NO)
        AppCompatDelegate.setDefaultNightMode(theme)
    }

    private fun plusPresenterSubComponent(): PresenterSubComponent =
        presenterSubComponent ?: appComponent.presenterSubComponent()
            .mappersModule(MappersModule())
            .speechRecognitionModule(SpeechRecognitionModule())
            .dateModule(DateModule())
            .searchModule(SearchModule())
            .build()
            .also { presenterSubComponent = it }

    private fun plusActivitySubComponent(activity: AppCompatActivity): ActivitySubComponent? =
        activitySubComponent ?: presenterSubComponent?.activitySubComponent()
            ?.activityModule(ActivityModule(activity))
            ?.build()
            .also { activitySubComponent = it }

    private fun clearActivitySubComponent() {
        activitySubComponent = null
    }

    private fun clearActivityPerInstanceSubComponent() {
        presenterSubComponent = null
    }
}