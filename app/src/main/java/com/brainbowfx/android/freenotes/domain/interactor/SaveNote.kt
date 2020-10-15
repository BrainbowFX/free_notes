package com.brainbowfx.android.freenotes.domain.interactor

import com.brainbowfx.android.freenotes.domain.entities.Note
import com.brainbowfx.android.freenotes.domain.repository.NotesRepository
import javax.inject.Inject

class SaveNote @Inject constructor(private val notesRepository: NotesRepository) :
    UseCase<Note, Long?>() {

    override suspend fun execute(note: Note): Long? = if (note.id == 0L) {
        notesRepository.add(note)
    } else {
        notesRepository.update(note)
        null
    }

}