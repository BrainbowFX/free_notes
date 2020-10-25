package com.brainbowfx.android.freenotes.domain.interactor

import com.brainbowfx.android.freenotes.data.database.models.NoteEntity
import com.brainbowfx.android.freenotes.domain.entities.Note
import com.brainbowfx.android.freenotes.domain.mappers.Mapper
import com.brainbowfx.android.freenotes.domain.repository.NotesRepository
import javax.inject.Inject

class Search @Inject constructor(
    private val notesRepository: NotesRepository,
    private val noteEntityToNoteMapper: Mapper<NoteEntity, Note>
) : UseCase<String, List<Note>>() {
    override suspend fun execute(query: String): List<Note> =
        notesRepository.find(query).map(noteEntityToNoteMapper::map)
}