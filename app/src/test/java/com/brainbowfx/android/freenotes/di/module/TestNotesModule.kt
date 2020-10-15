package com.brainbowfx.android.freenotes.di.module

import com.brainbowfx.android.freenotes.domain.repository.NotesRepository
import dagger.Module
import dagger.Provides
import org.mockito.Mockito

@Module
class TestNotesModule {

    @Provides
    fun provideNotesRepository(): NotesRepository = Mockito.mock(NotesRepository::class.java)
}