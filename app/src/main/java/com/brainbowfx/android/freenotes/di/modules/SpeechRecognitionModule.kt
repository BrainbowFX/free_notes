package com.brainbowfx.android.freenotes.di.modules

import android.content.Context
import android.content.Intent
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import com.brainbowfx.android.freenotes.RECOGNIZER_INTENT_ID
import com.brainbowfx.android.freenotes.di.scopes.ActivityPerInstance
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
class SpeechRecognitionModule {

    @Provides
    @ActivityPerInstance
    fun provideSpeechRecognizer(applicationContext: Context): SpeechRecognizer =
        SpeechRecognizer.createSpeechRecognizer(applicationContext)

    @Provides
    @ActivityPerInstance
    @Named(RECOGNIZER_INTENT_ID)
    fun provideRecognizerIntent(): Intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)

}