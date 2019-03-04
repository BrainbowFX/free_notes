package com.brainbowfx.android.simplenotes.data.mappers

import com.brainbowfx.android.simplenotes.data.database.models.NoteEntity
import com.brainbowfx.android.simplenotes.domain.entities.Note
import com.brainbowfx.android.simplenotes.domain.mappers.Mapper

class NotesToNotesEntityMapper: Mapper<Note, NoteEntity> {
    override fun map(input: Note): NoteEntity {
        return NoteEntity(
            if (input.id == -1) null else input.id,
            input.title,
            input.text,
            input.imagePaths)
    }

}