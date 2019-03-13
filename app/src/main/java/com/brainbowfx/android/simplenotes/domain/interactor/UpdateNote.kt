package com.brainbowfx.android.simplenotes.domain.interactor

import com.brainbowfx.android.simplenotes.domain.CoroutineDispatchersProvider
import com.brainbowfx.android.simplenotes.domain.entities.Note
import javax.inject.Inject

class UpdateNote @Inject constructor(coroutineDispatchersProvider: CoroutineDispatchersProvider) :
    NotesUseCases<Note, Unit>(coroutineDispatchersProvider) {

    override suspend fun run(params: Note) {
        notesRepository.update(params)
    }

}