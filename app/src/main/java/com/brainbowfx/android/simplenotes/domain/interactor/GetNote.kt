package com.brainbowfx.android.simplenotes.domain.interactor

import com.brainbowfx.android.simplenotes.domain.entities.Note
import javax.inject.Inject

class GetNote @Inject constructor() : NotesUseCases<Long, Note>() {

    override suspend fun execute(params: Long): Note = notesRepository.get(params)
}