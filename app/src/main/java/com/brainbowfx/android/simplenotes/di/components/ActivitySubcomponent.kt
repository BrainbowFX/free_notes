package com.brainbowfx.android.simplenotes.di.components

import com.brainbowfx.android.simplenotes.di.modules.ActivityModule
import com.brainbowfx.android.simplenotes.di.modules.DateModule
import com.brainbowfx.android.simplenotes.di.modules.SpeechRecognitionModule
import com.brainbowfx.android.simplenotes.di.scopes.Activity
import com.brainbowfx.android.simplenotes.presentation.MainActivity
import dagger.Subcomponent

@Subcomponent(modules = [ActivityModule::class, DateModule::class, SpeechRecognitionModule::class])
@Activity
interface ActivitySubcomponent {
    fun inject(mainActivity: MainActivity)
}