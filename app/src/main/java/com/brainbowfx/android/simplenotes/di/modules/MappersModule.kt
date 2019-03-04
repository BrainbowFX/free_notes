package com.brainbowfx.android.simplenotes.di.modules

import com.brainbowfx.android.simplenotes.data.database.models.NoteEntity
import com.brainbowfx.android.simplenotes.data.mappers.NotesEntityToNotesMapper
import com.brainbowfx.android.simplenotes.data.mappers.NotesToNotesEntityMapper
import com.brainbowfx.android.simplenotes.domain.entities.Note
import com.brainbowfx.android.simplenotes.domain.mappers.Mapper
import dagger.Module
import dagger.Provides

@Module
class MappersModule {

    @Provides
    fun provideNotesToNotesEntityMapper(): Mapper<Note, NoteEntity> = NotesToNotesEntityMapper()

    @Provides
    fun provideNotesEntityToNotesMapper(): Mapper<NoteEntity, Note> = NotesEntityToNotesMapper()

}