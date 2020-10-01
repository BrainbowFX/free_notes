package com.brainbowfx.android.freenotes.domain.interactor

import com.brainbowfx.android.freenotes.di.scopes.ActivityPerInstance
import com.brainbowfx.android.freenotes.domain.entities.Note
import com.brainbowfx.android.freenotes.domain.repository.NotesRepository
import javax.inject.Inject

@ActivityPerInstance
class DeleteNote @Inject constructor(private val notesRepository: NotesRepository) : UseCase<Note, Unit>() {

    override suspend fun execute(params: Note) {
        notesRepository.delete(params)
    }
}