package com.brainbowfx.android.simplenotes.di.modules

import android.content.Context
import android.content.Intent
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import dagger.Module
import dagger.Provides

@Module
class SpeechRecognitionModule {

    @Provides
    fun provideSpeechRecognizer(applicationContext: Context): SpeechRecognizer =
        SpeechRecognizer.createSpeechRecognizer(applicationContext)

    @Provides
    fun provideRecognizerIntent(): Intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)

}