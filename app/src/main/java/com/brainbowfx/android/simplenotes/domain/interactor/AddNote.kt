package com.brainbowfx.android.simplenotes.domain.interactor

import com.brainbowfx.android.simplenotes.domain.entities.Note
import javax.inject.Inject

class AddNote @Inject constructor() : NotesUseCases<Note, Long>() {

    override suspend fun execute(params: Note): Long = notesRepository.add(params)

}