package com.brainbowfx.android.freenotes.data.mappers

import com.brainbowfx.android.freenotes.data.database.models.NoteEntity
import com.brainbowfx.android.freenotes.domain.entities.Note
import com.brainbowfx.android.freenotes.domain.mappers.Mapper
import java.text.SimpleDateFormat

class NotesToNotesEntityMapper(private val simpleDateFormat: SimpleDateFormat) : Mapper<Note, NoteEntity> {
    override fun map(input: Note): NoteEntity =
        if (input.id == -1L) NoteEntity(
            title = input.title,
            text = input.text,
            date = simpleDateFormat.parse(input.dateTime).time,
            imagePaths = input.imagePaths
        ) else NoteEntity(
            input.id,
            input.title,
            input.text,
            simpleDateFormat.parse(input.dateTime).time,
            input.imagePaths
        )

}