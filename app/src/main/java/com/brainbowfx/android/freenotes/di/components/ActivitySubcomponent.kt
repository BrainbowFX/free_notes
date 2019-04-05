package com.brainbowfx.android.freenotes.di.components

import com.brainbowfx.android.freenotes.di.modules.*
import com.brainbowfx.android.freenotes.di.modules.binders.ActivityBindersModule
import com.brainbowfx.android.freenotes.di.scopes.Activity
import com.brainbowfx.android.freenotes.presentation.view.NotesListFragment
import dagger.Subcomponent

@Subcomponent(modules = [DateModule::class, SpeechRecognitionModule::class, ActivityBindersModule::class, MappersModule::class])
@Activity
interface ActivitySubcomponent {

    fun activityPerInstanceSubcomponent(): ActivityPerInstanceSubcomponent.Builder

    @Subcomponent.Builder
    interface Builder {
        fun dateModule(dateModule: DateModule): ActivitySubcomponent.Builder
        fun speechRecognitionModule(speechRecognitionModule: SpeechRecognitionModule): ActivitySubcomponent.Builder
        fun mappersModule(mappersModule: MappersModule): ActivitySubcomponent.Builder
        fun build(): ActivitySubcomponent
    }

    fun inject(notesListFragment: NotesListFragment)
}