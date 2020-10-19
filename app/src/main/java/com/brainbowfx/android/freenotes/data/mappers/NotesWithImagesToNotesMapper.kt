package com.brainbowfx.android.freenotes.data.mappers

import com.brainbowfx.android.freenotes.data.database.models.ImageEntity
import com.brainbowfx.android.freenotes.data.database.models.projections.NoteWithImages
import com.brainbowfx.android.freenotes.domain.entities.Image
import com.brainbowfx.android.freenotes.domain.entities.Note
import com.brainbowfx.android.freenotes.domain.mappers.Mapper
import java.text.SimpleDateFormat
import java.util.*

class NotesWithImagesToNotesMapper(
    private val simpleDateFormat: SimpleDateFormat,
    private val imagesMapper: Mapper<ImageEntity, Image>
) :
    Mapper<NoteWithImages, Note> {
    override fun map(input: NoteWithImages): Note = with(input) {
        Note(
            id = noteEntity.id,
            title = noteEntity.title,
            text = noteEntity.text,
            dateTime = simpleDateFormat.format(Date(noteEntity.date)),
            images = images.map(imagesMapper::map).toMutableList()
        )
    }

}