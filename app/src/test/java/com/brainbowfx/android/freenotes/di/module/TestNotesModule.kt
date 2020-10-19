package com.brainbowfx.android.freenotes.di.module

import com.brainbowfx.android.freenotes.DATETIME
import com.brainbowfx.android.freenotes.DATETIME_NAMED_ID
import com.brainbowfx.android.freenotes.TIMESTAMP
import com.brainbowfx.android.freenotes.TIMESTAMP_NAMED_ID
import com.brainbowfx.android.freenotes.data.database.models.ImageEntity
import com.brainbowfx.android.freenotes.data.database.models.NoteEntity
import com.brainbowfx.android.freenotes.data.database.models.projections.NoteWithImages
import com.brainbowfx.android.freenotes.data.mappers.ImageToImageEntityMapper
import com.brainbowfx.android.freenotes.data.mappers.NoteToNoteEntityMapper
import com.brainbowfx.android.freenotes.data.mappers.NotesToNotesWithImagesMapper
import com.brainbowfx.android.freenotes.di.scopes.Presenter
import com.brainbowfx.android.freenotes.domain.entities.Image
import com.brainbowfx.android.freenotes.domain.entities.Note
import com.brainbowfx.android.freenotes.domain.mappers.Mapper
import com.brainbowfx.android.freenotes.domain.repository.NotesRepository
import dagger.Module
import dagger.Provides
import org.mockito.Mockito
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Named

@Module
class TestNotesModule {

    @Provides
    fun provideDefaultLocale(): Locale = Locale.getDefault()

    @Provides
    fun provideDateTimeSimpleDateFormat(locale: Locale): SimpleDateFormat = SimpleDateFormat(
        DATETIME, locale
    )

    @Provides
    fun provideNotesRepository(): NotesRepository = Mockito.mock(NotesRepository::class.java)

    @Provides
    fun provideNoteToNoteEntityMapper(simpleDateFormat: SimpleDateFormat): Mapper<Note, NoteEntity> =
        NoteToNoteEntityMapper(simpleDateFormat)

    @Provides
    fun provideImageToImageEntityMapper(): Mapper<Image, ImageEntity> = ImageToImageEntityMapper()

    @Provides
    fun provideNoteToNoteWithImagesMapper(
        noteToNoteEntityMapper: Mapper<Note, NoteEntity>,
        imageToImageEntityMapper: Mapper<Image, ImageEntity>
    ): Mapper<Note, NoteWithImages> =
        NotesToNotesWithImagesMapper(noteToNoteEntityMapper, imageToImageEntityMapper)
}