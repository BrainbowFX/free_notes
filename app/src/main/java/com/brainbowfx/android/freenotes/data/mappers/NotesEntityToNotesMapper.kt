package com.brainbowfx.android.freenotes.data.mappers

import com.brainbowfx.android.freenotes.data.database.models.NoteEntity
import com.brainbowfx.android.freenotes.domain.entities.Note
import com.brainbowfx.android.freenotes.domain.mappers.Mapper
import java.text.SimpleDateFormat
import java.util.*

class NotesEntityToNotesMapper(private val simpleDateFormat: SimpleDateFormat) :
    Mapper<NoteEntity, Note> {
    override fun map(input: NoteEntity): Note {
        return Note(
            input.id,
            input.title,
            input.text,
            simpleDateFormat.format(Date(input.date)),
            input.imagePaths.toMutableList()
        )

    }

}