package com.brainbowfx.android.freenotes.data.mappers

import com.brainbowfx.android.freenotes.data.database.models.NoteEntity
import com.brainbowfx.android.freenotes.domain.entities.Note
import com.brainbowfx.android.freenotes.domain.mappers.Mapper
import java.text.SimpleDateFormat
import java.util.*

class NoteToNoteEntityMapper(private val simpleDateFormat: SimpleDateFormat) :
    Mapper<Note, NoteEntity> {
    override fun map(input: Note): NoteEntity {
        val date = simpleDateFormat.parse(input.dateTime)?.time ?: Date().time

        return NoteEntity(
            input.id,
            input.title,
            input.text,
            date
        )
    }
}