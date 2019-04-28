package com.brainbowfx.android.freenotes.domain.interactor

import com.brainbowfx.android.freenotes.domain.entities.Note
import com.brainbowfx.android.freenotes.domain.repository.NotesRepository
import javax.inject.Inject

class GetNote @Inject constructor(private val notesRepository: NotesRepository) : UseCase<Long, Note>() {

    override suspend fun execute(params: Long): Note = notesRepository.get(params)
}