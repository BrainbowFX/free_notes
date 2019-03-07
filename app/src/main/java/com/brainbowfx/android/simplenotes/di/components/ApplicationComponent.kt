package com.brainbowfx.android.simplenotes.di.components

import com.brainbowfx.android.simplenotes.di.modules.*
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, DatabaseModule::class, FilesModule::class])
interface ApplicationComponent {
    fun plusActivitySubcomponent(dateModule: DateModule, activityModule: ActivityModule, speechRecognitionModule: SpeechRecognitionModule): ActivitySubcomponent

}