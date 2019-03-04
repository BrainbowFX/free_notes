package com.brainbowfx.android.simplenotes.di.components

import com.brainbowfx.android.simplenotes.di.modules.AppModule
import com.brainbowfx.android.simplenotes.di.modules.DatabaseModule
import com.brainbowfx.android.simplenotes.di.modules.SpeechRecognitionModule
import com.brainbowfx.android.simplenotes.presentation.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, DatabaseModule::class, SpeechRecognitionModule::class])
interface ApplicationComponent {
    fun inject(mainActivity: MainActivity)

}