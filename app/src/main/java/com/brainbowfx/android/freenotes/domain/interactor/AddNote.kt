package com.brainbowfx.android.freenotes.domain.interactor

import com.brainbowfx.android.freenotes.domain.entities.Note
import com.brainbowfx.android.freenotes.domain.repository.NotesRepository
import javax.inject.Inject

class AddNote @Inject constructor(private val notesRepository: NotesRepository) : UseCase<Note, Long>() {

    override suspend fun execute(params: Note): Long = notesRepository.add(params)

}