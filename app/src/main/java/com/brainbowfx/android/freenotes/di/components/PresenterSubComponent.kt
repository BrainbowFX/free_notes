package com.brainbowfx.android.freenotes.di.components

import com.brainbowfx.android.freenotes.di.modules.*
import com.brainbowfx.android.freenotes.di.modules.binders.PresenterBindersModule
import com.brainbowfx.android.freenotes.di.scopes.Presenter
import com.brainbowfx.android.freenotes.presentation.view.NotesListFragment
import dagger.Subcomponent

@Subcomponent(
    modules = [
        DateModule::class,
        SpeechRecognitionModule::class,
        PresenterBindersModule::class,
        MappersModule::class
    ]
)
@Presenter
interface PresenterSubComponent {

    fun activitySubComponent(): ActivitySubComponent.Builder

    @Subcomponent.Builder
    interface Builder {
        fun dateModule(dateModule: DateModule): Builder
        fun speechRecognitionModule(speechRecognitionModule: SpeechRecognitionModule): Builder
        fun mappersModule(mappersModule: MappersModule): Builder
        fun build(): PresenterSubComponent
    }
}