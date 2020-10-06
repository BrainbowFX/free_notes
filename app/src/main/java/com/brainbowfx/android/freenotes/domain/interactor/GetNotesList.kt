package com.brainbowfx.android.freenotes.domain.interactor

import com.brainbowfx.android.freenotes.di.scopes.ActivityPerInstance
import com.brainbowfx.android.freenotes.domain.entities.Note
import com.brainbowfx.android.freenotes.domain.repository.NotesRepository
import javax.inject.Inject

@ActivityPerInstance
class GetNotesList @Inject constructor(private val notesRepository: NotesRepository) : UseCase<Boolean, List<Note>>() {

    override suspend fun execute(recycled: Boolean): List<Note> = notesRepository.getAll(recycled)
}