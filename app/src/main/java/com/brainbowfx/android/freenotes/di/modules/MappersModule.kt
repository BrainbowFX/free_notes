package com.brainbowfx.android.freenotes.di.modules

import android.net.Uri
import com.brainbowfx.android.freenotes.DATETIME_NAMED_ID
import com.brainbowfx.android.freenotes.data.database.models.NoteEntity
import com.brainbowfx.android.freenotes.data.mappers.UrlToUriMapper
import com.brainbowfx.android.freenotes.data.mappers.NotesEntityToNotesMapper
import com.brainbowfx.android.freenotes.data.mappers.NotesToNotesEntityMapper
import com.brainbowfx.android.freenotes.di.scopes.Activity
import com.brainbowfx.android.freenotes.domain.entities.Note
import com.brainbowfx.android.freenotes.domain.mappers.Mapper
import dagger.Module
import dagger.Provides
import java.text.SimpleDateFormat
import javax.inject.Named

@Module
class MappersModule {

    @Provides
    @Activity
    fun provideNotesToNotesEntityMapper(@Named(DATETIME_NAMED_ID) simpleDateFormat: SimpleDateFormat): Mapper<Note, NoteEntity> =
        NotesToNotesEntityMapper(simpleDateFormat)

    @Provides
    @Activity
    fun provideNotesEntityToNotesMapper(@Named(DATETIME_NAMED_ID) simpleDateFormat: SimpleDateFormat): Mapper<NoteEntity, Note> =
        NotesEntityToNotesMapper(simpleDateFormat)

    @Provides
    @Activity
    fun provideUrlToUriMapper(): Mapper<String, Uri> = UrlToUriMapper()

}