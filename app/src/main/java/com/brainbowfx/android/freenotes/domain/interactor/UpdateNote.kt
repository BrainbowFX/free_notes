package com.brainbowfx.android.freenotes.domain.interactor

import com.brainbowfx.android.freenotes.domain.entities.Note
import com.brainbowfx.android.freenotes.domain.repository.NotesRepository
import javax.inject.Inject

class UpdateNote @Inject constructor(private val notesRepository: NotesRepository) : UseCase<Note, Unit>() {

    override suspend fun execute(params: Note) {
        notesRepository.update(params)
    }

}