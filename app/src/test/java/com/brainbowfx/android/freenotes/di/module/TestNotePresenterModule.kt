package com.brainbowfx.android.freenotes.di.module

import com.brainbowfx.android.freenotes.DATETIME
import com.brainbowfx.android.freenotes.DATETIME_NAMED_ID
import com.brainbowfx.android.freenotes.domain.abstraction.ImageViewer
import com.brainbowfx.android.freenotes.domain.interactor.SaveNote
import com.brainbowfx.android.freenotes.domain.interactor.GetNote
import com.brainbowfx.android.freenotes.domain.router.NotesRouter
import dagger.Module
import dagger.Provides
import org.mockito.Mockito
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Named

@Module
class TestNotePresenterModule {

    @Provides
    fun provideImageViewerMock(): ImageViewer = Mockito.mock(ImageViewer::class.java)

    @Provides
    fun provideGetNoteMock(): GetNote = Mockito.mock(GetNote::class.java)

    @Provides
    fun provideAddNoteMock(): SaveNote = Mockito.mock(SaveNote::class.java)

    @Provides
    @Named(DATETIME_NAMED_ID)
    fun provideSimpleDateFormat(): SimpleDateFormat = SimpleDateFormat(DATETIME, Locale.getDefault())

    @Provides
    fun provideNotesRouter(): NotesRouter = Mockito.mock(NotesRouter::class.java)
}