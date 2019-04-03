package com.brainbowfx.android.simplenotes.domain.interactor

import com.brainbowfx.android.simplenotes.di.scopes.Activity
import com.brainbowfx.android.simplenotes.domain.entities.Note
import javax.inject.Inject

@Activity
class DeleteNote @Inject constructor() : NotesUseCases<Note, Unit>() {

    override suspend fun execute(params: Note) {
        notesRepository.delete(params)
    }
}