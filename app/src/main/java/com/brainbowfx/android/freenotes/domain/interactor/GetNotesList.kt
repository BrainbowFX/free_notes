package com.brainbowfx.android.freenotes.domain.interactor

import com.brainbowfx.android.freenotes.di.scopes.Activity
import com.brainbowfx.android.freenotes.domain.entities.Note
import javax.inject.Inject

@Activity
class GetNotesList @Inject constructor() : NotesUseCases<Unit, List<Note>>() {

    override suspend fun execute(params: Unit): List<Note> = notesRepository.getAll()
}