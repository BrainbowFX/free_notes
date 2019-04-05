package com.brainbowfx.android.freenotes.domain.interactor

import com.brainbowfx.android.freenotes.di.scopes.Activity
import com.brainbowfx.android.freenotes.domain.entities.Note
import javax.inject.Inject

@Activity
class DeleteNote @Inject constructor() : NotesUseCases<Note, Unit>() {

    override suspend fun execute(params: Note) {
        notesRepository.delete(params)
    }
}