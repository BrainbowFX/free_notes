package com.brainbowfx.android.simplenotes.di.modules.binders

import com.brainbowfx.android.simplenotes.data.database.NotesRepositoryImpl
import com.brainbowfx.android.simplenotes.domain.repository.NotesRepository
import com.brainbowfx.android.simplenotes.di.scopes.Activity
import dagger.Binds
import dagger.Module

@Module
abstract class ActivityBindersModule {

    @Activity
    @Binds
    abstract fun bindNotesRepository(notesRepositoryImpl: NotesRepositoryImpl): NotesRepository
}