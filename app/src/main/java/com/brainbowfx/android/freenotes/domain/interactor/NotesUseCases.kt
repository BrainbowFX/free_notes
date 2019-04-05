package com.brainbowfx.android.freenotes.domain.interactor

import com.brainbowfx.android.freenotes.domain.repository.NotesRepository
import javax.inject.Inject

abstract class NotesUseCases<in I, out T> : UseCase<I, T>() {

    @Inject lateinit var notesRepository: NotesRepository
}