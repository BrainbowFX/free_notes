package com.brainbowfx.android.simplenotes.domain.interactor

import com.brainbowfx.android.simplenotes.domain.entities.Note
import javax.inject.Inject

class UpdateNote @Inject constructor() : NotesUseCases<Note, Unit>() {

    override suspend fun execute(params: Note) {
        notesRepository.update(params)
    }

}