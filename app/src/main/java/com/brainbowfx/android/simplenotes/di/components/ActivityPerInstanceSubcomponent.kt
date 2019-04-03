package com.brainbowfx.android.simplenotes.di.components

import com.brainbowfx.android.simplenotes.di.modules.*
import com.brainbowfx.android.simplenotes.di.modules.binders.ActivityPerInstanceBindersModule
import com.brainbowfx.android.simplenotes.di.scopes.ActivityPerInstance
import com.brainbowfx.android.simplenotes.presentation.presenter.ImagesPresenter
import com.brainbowfx.android.simplenotes.presentation.presenter.NotesListPresenter
import com.brainbowfx.android.simplenotes.presentation.presenter.SpeechPresenter
import com.brainbowfx.android.simplenotes.presentation.presenter.NotePresenter
import com.brainbowfx.android.simplenotes.presentation.view.MainActivity
import com.brainbowfx.android.simplenotes.presentation.view.NotesEditFragment
import dagger.Subcomponent

@Subcomponent(modules = [ActivityModule::class, ActivityPerInstanceBindersModule::class])
@ActivityPerInstance
interface ActivityPerInstanceSubcomponent {

    @Subcomponent.Builder
    interface Builder {
        fun activityModule(activityModule: ActivityModule): ActivityPerInstanceSubcomponent.Builder
        fun build(): ActivityPerInstanceSubcomponent
    }

    fun inject(notesListPresenter: NotesListPresenter)
    fun inject(notesEditFragment: NotesEditFragment)
    fun inject(mainActivity: MainActivity)
    fun inject(speechPresenter: SpeechPresenter)
    fun inject(notePresenter: NotePresenter)
    fun inject(imagesPresenter: ImagesPresenter)
}