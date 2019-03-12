package com.brainbowfx.android.simplenotes.di.modules

import com.brainbowfx.android.simplenotes.data.database.models.NoteEntity
import com.brainbowfx.android.simplenotes.data.mappers.NotesEntityToNotesMapper
import com.brainbowfx.android.simplenotes.data.mappers.NotesToNotesEntityMapper
import com.brainbowfx.android.simplenotes.di.scopes.Activity
import com.brainbowfx.android.simplenotes.domain.entities.Note
import com.brainbowfx.android.simplenotes.domain.mappers.Mapper
import dagger.Module
import dagger.Provides
import java.text.SimpleDateFormat
import javax.inject.Named

@Module
class MappersModule {

    @Provides
    @Activity
    fun provideNotesToNotesEntityMapper(@Named("DateTime") simpleDateFormat: SimpleDateFormat): Mapper<Note, NoteEntity> =
        NotesToNotesEntityMapper(simpleDateFormat)

    @Provides
    @Activity
    fun provideNotesEntityToNotesMapper(@Named("DateTime") simpleDateFormat: SimpleDateFormat): Mapper<NoteEntity, Note> =
        NotesEntityToNotesMapper(simpleDateFormat)

}