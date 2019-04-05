package com.brainbowfx.android.freenotes.di.modules

import android.content.Context
import android.content.Intent
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import com.brainbowfx.android.freenotes.di.scopes.Activity
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
class SpeechRecognitionModule {

    @Provides
    @Activity
    fun provideSpeechRecognizer(applicationContext: Context): SpeechRecognizer =
        SpeechRecognizer.createSpeechRecognizer(applicationContext)

    @Provides
    @Activity
    @Named("RecognizerIntent")
    fun provideRecognizerIntent(): Intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)

}