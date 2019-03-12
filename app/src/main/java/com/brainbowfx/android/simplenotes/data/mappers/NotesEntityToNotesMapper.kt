package com.brainbowfx.android.simplenotes.data.mappers

import com.brainbowfx.android.simplenotes.data.database.models.NoteEntity
import com.brainbowfx.android.simplenotes.domain.entities.Note
import com.brainbowfx.android.simplenotes.domain.mappers.Mapper
import java.text.SimpleDateFormat
import java.util.*

class NotesEntityToNotesMapper(private var simpleDateFormat: SimpleDateFormat) :
    Mapper<NoteEntity, Note> {
    override fun map(input: NoteEntity): Note {
        return Note(
            input.id ?: -1,
            input.title,
            input.text,
            simpleDateFormat.format(Date(input.date)),
            input.imagePaths.toMutableList()
        )

    }

}