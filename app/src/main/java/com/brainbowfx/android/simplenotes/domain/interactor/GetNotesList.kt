package com.brainbowfx.android.simplenotes.domain.interactor

import com.brainbowfx.android.simplenotes.domain.repository.NotesRepository
import com.brainbowfx.android.simplenotes.domain.entities.Note
import javax.inject.Inject

class GetNotesList @Inject constructor(val notesRepository: NotesRepository) : UseCase<Unit, List<Note>>() {

    override suspend fun run(params: Unit): List<Note> = notesRepository.getAll()
}