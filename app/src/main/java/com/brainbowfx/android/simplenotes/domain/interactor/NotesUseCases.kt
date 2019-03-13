package com.brainbowfx.android.simplenotes.domain.interactor

import com.brainbowfx.android.simplenotes.domain.CoroutineDispatchersProvider
import com.brainbowfx.android.simplenotes.domain.repository.NotesRepository
import javax.inject.Inject

abstract class NotesUseCases<in I, out T>(coroutineDispatchersProvider: CoroutineDispatchersProvider) :
    UseCase<I, T>(coroutineDispatchersProvider.getIODispatcher()) {

    @Inject lateinit var notesRepository: NotesRepository
}