package com.brainbowfx.android.freenotes.data.mappers

import com.brainbowfx.android.freenotes.data.database.models.NoteEntity
import com.brainbowfx.android.freenotes.domain.entities.Note
import com.brainbowfx.android.freenotes.domain.mappers.Mapper
import java.text.SimpleDateFormat
import java.util.*

class NoteEntityToNoteMapper(private val simpleDateFormat: SimpleDateFormat) :
    Mapper<NoteEntity, Note> {
    override fun map(input: NoteEntity): Note {
        val dateTime = simpleDateFormat.format(Date(input.date))

        return Note(
            id = input.id,
            title = input.title,
            text = input.text,
            dateTime = dateTime
        )
    }
}