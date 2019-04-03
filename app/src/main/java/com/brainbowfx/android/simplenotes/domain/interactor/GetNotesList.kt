package com.brainbowfx.android.simplenotes.domain.interactor

import com.brainbowfx.android.simplenotes.di.scopes.Activity
import com.brainbowfx.android.simplenotes.domain.entities.Note
import javax.inject.Inject

@Activity
class GetNotesList @Inject constructor() : NotesUseCases<Unit, List<Note>>() {

    override suspend fun execute(params: Unit): List<Note> = notesRepository.getAll()
}