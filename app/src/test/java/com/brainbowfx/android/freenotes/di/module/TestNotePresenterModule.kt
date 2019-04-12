package com.brainbowfx.android.freenotes.di.module

import com.brainbowfx.android.freenotes.di.modules.DATETIME
import com.brainbowfx.android.freenotes.domain.abstraction.ImageViewer
import com.brainbowfx.android.freenotes.domain.interactor.AddNote
import com.brainbowfx.android.freenotes.domain.interactor.GetNote
import com.brainbowfx.android.freenotes.domain.interactor.UpdateNote
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
    fun provideAddNoteMock(): AddNote = Mockito.mock(AddNote::class.java)

    @Provides
    fun provideUpdateNoteMock(): UpdateNote = Mockito.mock(UpdateNote::class.java)

    @Provides
    @Named("DateTime")
    fun provideSimpleDateFormat(): SimpleDateFormat = SimpleDateFormat(DATETIME, Locale.getDefault())
}