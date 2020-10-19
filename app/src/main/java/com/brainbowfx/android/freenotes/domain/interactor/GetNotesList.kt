package com.brainbowfx.android.freenotes.domain.interactor

import com.brainbowfx.android.freenotes.data.database.models.projections.NoteWithImages
import com.brainbowfx.android.freenotes.di.scopes.Presenter
import com.brainbowfx.android.freenotes.domain.entities.Note
import com.brainbowfx.android.freenotes.domain.mappers.Mapper
import com.brainbowfx.android.freenotes.domain.repository.NotesRepository
import javax.inject.Inject

@Presenter
class GetNotesList @Inject constructor(
    private val notesRepository: NotesRepository,
    private val noteWithImagesToNoteMapper: Mapper<NoteWithImages, Note>
) : UseCase<Boolean, List<Note>>() {

    override suspend fun execute(recycled: Boolean): List<Note> =
        notesRepository.getAll(recycled).map(noteWithImagesToNoteMapper::map)
}