package com.brainbowfx.android.simplenotes.data.mappers

import com.brainbowfx.android.simplenotes.data.database.models.NoteEntity
import com.brainbowfx.android.simplenotes.domain.entities.Note
import com.brainbowfx.android.simplenotes.domain.mappers.Mapper

class NotesEntityToNotesMapper: Mapper<NoteEntity, Note> {
    override fun map(input: NoteEntity): Note {
        return Note(
            input.id ?: -1,
            input.title,
            input.text,
            input.imagePaths.toMutableList())
    }

}