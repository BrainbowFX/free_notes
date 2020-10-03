package com.brainbowfx.android.freenotes.di.components

import com.brainbowfx.android.freenotes.di.modules.*
import com.brainbowfx.android.freenotes.di.modules.binders.ActivityPerInstanceBindersModule
import com.brainbowfx.android.freenotes.di.scopes.ActivityPerInstance
import com.brainbowfx.android.freenotes.presentation.view.NotesListFragment
import dagger.Subcomponent

@Subcomponent(modules = [DateModule::class, SpeechRecognitionModule::class, ActivityPerInstanceBindersModule::class, MappersModule::class])
@ActivityPerInstance
interface ActivityPerInstanceSubcomponent {

    fun activitySubComponent(): ActivitySubComponent.Builder

    @Subcomponent.Builder
    interface Builder {
        fun dateModule(dateModule: DateModule): Builder
        fun speechRecognitionModule(speechRecognitionModule: SpeechRecognitionModule): Builder
        fun mappersModule(mappersModule: MappersModule): Builder
        fun build(): ActivityPerInstanceSubcomponent
    }

    fun inject(notesListFragment: NotesListFragment)
}