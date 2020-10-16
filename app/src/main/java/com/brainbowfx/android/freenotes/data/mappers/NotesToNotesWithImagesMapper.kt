package com.brainbowfx.android.freenotes.data.mappers

import com.brainbowfx.android.freenotes.data.database.models.ImageEntity
import com.brainbowfx.android.freenotes.data.database.models.NoteEntity
import com.brainbowfx.android.freenotes.data.database.models.projections.NoteWithImages
import com.brainbowfx.android.freenotes.domain.entities.Image
import com.brainbowfx.android.freenotes.domain.entities.Note
import com.brainbowfx.android.freenotes.domain.mappers.Mapper
import java.text.SimpleDateFormat
import java.util.*

class NotesToNotesWithImagesMapper(
    private val noteToNoteEntityMapper: Mapper<Note, NoteEntity>,
    private val imageToImageEntityMapper: Mapper<Image, ImageEntity>
) :
    Mapper<Note, NoteWithImages> {
    override fun map(input: Note): NoteWithImages {
        val noteEntity = noteToNoteEntityMapper.map(input)
        val images = input.images.map(imageToImageEntityMapper::map)
        return NoteWithImages(noteEntity, images)
    }
}