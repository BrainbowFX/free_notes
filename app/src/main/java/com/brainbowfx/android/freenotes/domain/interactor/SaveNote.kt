package com.brainbowfx.android.freenotes.domain.interactor

import com.brainbowfx.android.freenotes.data.database.models.projections.NoteWithImages
import com.brainbowfx.android.freenotes.data.mappers.NotesToNotesWithImagesMapper
import com.brainbowfx.android.freenotes.domain.entities.Note
import com.brainbowfx.android.freenotes.domain.mappers.Mapper
import com.brainbowfx.android.freenotes.domain.repository.NotesRepository
import javax.inject.Inject

class SaveNote @Inject constructor(
    private val notesRepository: NotesRepository,
    private val noteToNotesWithImagesMapper: Mapper<Note, NoteWithImages>
) :
    UseCase<Note, Long?>() {

    override suspend fun execute(note: Note): Long? {
        val noteWithImage = noteToNotesWithImagesMapper.map(note)
        return if (note.id == 0L) {
            notesRepository.add(noteWithImage)
        } else {
            notesRepository.update(noteWithImage)
            null
        }
    }

}