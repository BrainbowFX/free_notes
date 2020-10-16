package com.brainbowfx.android.freenotes.domain.interactor

import com.brainbowfx.android.freenotes.data.database.models.NoteEntity
import com.brainbowfx.android.freenotes.di.scopes.Presenter
import com.brainbowfx.android.freenotes.domain.entities.Note
import com.brainbowfx.android.freenotes.domain.mappers.Mapper
import com.brainbowfx.android.freenotes.domain.repository.NotesRepository
import javax.inject.Inject

@Presenter
class DeleteNote @Inject constructor(
    private val notesRepository: NotesRepository,
    private val noteToNoteEntityMapper: Mapper<Note, NoteEntity>
) : UseCase<Note, Unit>() {

    override suspend fun execute(params: Note) {
        val noteEntity = noteToNoteEntityMapper.map(params)
        notesRepository.delete(noteEntity)
    }
}