package com.brainbowfx.android.freenotes.di.components

import com.brainbowfx.android.freenotes.di.modules.*
import com.brainbowfx.android.freenotes.di.modules.binders.ActivityBindersModule
import com.brainbowfx.android.freenotes.di.scopes.Activity
import com.brainbowfx.android.freenotes.presentation.presenter.*
import com.brainbowfx.android.freenotes.presentation.view.MainActivity
import com.brainbowfx.android.freenotes.presentation.view.NotesEditFragment
import com.brainbowfx.android.freenotes.presentation.view.NotesListFragment
import com.brainbowfx.android.freenotes.presentation.view.SearchFragment
import dagger.Subcomponent

@Subcomponent(modules = [ActivityModule::class, ActivityBindersModule::class])
@Activity
interface ActivitySubComponent {

    @Subcomponent.Builder
    interface Builder {
        fun activityModule(activityModule: ActivityModule): Builder
        fun build(): ActivitySubComponent
    }

    fun inject(notesListPresenter: NotesListPresenter)
    fun inject(notesEditFragment: NotesEditFragment)
    fun inject(mainActivity: MainActivity)
    fun inject(speechPresenter: SpeechPresenter)
    fun inject(notePresenter: NotePresenter)
    fun inject(imagesPresenter: ImagesPresenter)
    fun inject(notesListFragment: NotesListFragment)
    fun inject(searchFragment: SearchFragment)
    fun inject(searchPresenter: SearchPresenter)
}