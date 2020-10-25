package com.brainbowfx.android.freenotes.data.mappers

import com.brainbowfx.android.freenotes.data.database.models.ImageEntity
import com.brainbowfx.android.freenotes.data.database.models.NoteEntity
import com.brainbowfx.android.freenotes.data.database.models.projections.NoteWithImages
import com.brainbowfx.android.freenotes.domain.entities.Image
import com.brainbowfx.android.freenotes.domain.entities.Note
import com.brainbowfx.android.freenotes.domain.mappers.Mapper
import java.text.SimpleDateFormat
import java.util.*

class NotesWithImagesToNotesMapper(
    private val imagesMapper: Mapper<ImageEntity, Image>,
    private val notesMapper: Mapper<NoteEntity, Note>
) :
    Mapper<NoteWithImages, Note> {
    override fun map(input: NoteWithImages): Note = notesMapper
        .map(input.noteEntity)
        .apply { this.images = input.images.map(imagesMapper::map).toMutableList() }

}