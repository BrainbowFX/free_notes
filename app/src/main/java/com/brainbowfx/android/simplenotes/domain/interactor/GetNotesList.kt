package com.brainbowfx.android.simplenotes.domain.interactor

import com.brainbowfx.android.simplenotes.domain.CoroutineDispatchersProvider
import com.brainbowfx.android.simplenotes.domain.repository.NotesRepository
import com.brainbowfx.android.simplenotes.domain.entities.Note
import javax.inject.Inject

class GetNotesList @Inject constructor(coroutineDispatchersProvider: CoroutineDispatchersProvider
) : NotesUseCases<Unit, List<Note>>(coroutineDispatchersProvider) {

    override suspend fun run(params: Unit): List<Note> = notesRepository.getAll()
}