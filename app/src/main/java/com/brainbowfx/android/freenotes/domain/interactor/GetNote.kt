package com.brainbowfx.android.freenotes.domain.interactor

import com.brainbowfx.android.freenotes.data.database.models.projections.NoteWithImages
import com.brainbowfx.android.freenotes.domain.entities.Note
import com.brainbowfx.android.freenotes.domain.mappers.Mapper
import com.brainbowfx.android.freenotes.domain.repository.NotesRepository
import javax.inject.Inject

class GetNote @Inject constructor(
    private val notesRepository: NotesRepository,
    private val noteWithImagesToNoteMapper: Mapper<NoteWithImages, Note>
) : UseCase<Long, Note?>() {

    override suspend fun execute(params: Long): Note? =
        notesRepository.get(params)?.let(noteWithImagesToNoteMapper::map)
}