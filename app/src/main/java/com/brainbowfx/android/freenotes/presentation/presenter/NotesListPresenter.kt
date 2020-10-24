package com.brainbowfx.android.freenotes.presentation.presenter

import com.arellomobile.mvp.InjectViewState
import com.brainbowfx.android.freenotes.domain.CoroutineDispatchersProvider
import com.brainbowfx.android.freenotes.domain.entities.Note
import com.brainbowfx.android.freenotes.domain.interactor.DeleteNote
import com.brainbowfx.android.freenotes.domain.interactor.GetNotesList
import com.brainbowfx.android.freenotes.domain.router.NotesRouter
import com.brainbowfx.android.freenotes.domain.router.Router
import com.brainbowfx.android.freenotes.presentation.view.contract.NotesListView
import kotlinx.coroutines.*
import javax.inject.Inject

@InjectViewState
class NotesListPresenter : ScopedPresenter<NotesListView>() {

    @Inject
    lateinit var getNotesList: GetNotesList

    @Inject
    lateinit var deleteNote: DeleteNote

    @Inject
    lateinit var notesRouter: NotesRouter

    @Inject
    lateinit var searchRouter: Router

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.setupButton()
    }

    fun start() {
        launch {
            val result = getNotesList.execute(false)
            viewState.setData(result.toMutableList())
        }
    }

    override fun onDestroy() {
        coroutineContext[Job]?.cancel()
        super.onDestroy()
    }

    fun onNoteSelected(note: Note) {
        notesRouter.navigateNext(note.id)
    }

    fun onNoteDeleted(note: Note, position: Int) {
        launch {
            deleteNote.execute(note)
            viewState.removeNoteAt(position)
        }
    }

    fun onNoteDuplicated(note: Note) {
        notesRouter.navigateNext(note.id, true)
    }

    fun onFloatingButtonClick() {
        notesRouter.navigateNext()
    }

    fun onSearchButton() {
        searchRouter.navigateNext()
    }

}