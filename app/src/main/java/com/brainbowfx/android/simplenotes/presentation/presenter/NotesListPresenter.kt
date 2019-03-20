package com.brainbowfx.android.simplenotes.presentation.presenter

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.brainbowfx.android.simplenotes.domain.CoroutineDispatchersProvider
import com.brainbowfx.android.simplenotes.domain.entities.Note
import com.brainbowfx.android.simplenotes.domain.interactor.DeleteNote
import com.brainbowfx.android.simplenotes.domain.interactor.GetNotesList
import com.brainbowfx.android.simplenotes.domain.router.Router
import com.brainbowfx.android.simplenotes.presentation.view.contract.NotesListView
import kotlinx.coroutines.*
import java.io.IOException
import javax.inject.Inject

@InjectViewState
class NotesListPresenter @Inject constructor() : MvpPresenter<NotesListView>() {

    private var job: Job? = null

    @Inject
    lateinit var getNotesList: GetNotesList

    @Inject
    lateinit var deleteNote: DeleteNote

    @Inject
    lateinit var dispatchersProvider: CoroutineDispatchersProvider

    @Inject
    lateinit var router: Router

    fun start() {
        job = GlobalScope.launch(dispatchersProvider.getMainDispatcher()) {
            val result = withContext(dispatchersProvider.getIODispatcher()) { getNotesList.execute(Unit) }
            viewState.setData(result.toMutableList())
        }
    }


    override fun onDestroy() {
        job?.cancel()
        super.onDestroy()
    }

    fun onNoteSelected(note: Note) {
        router.navigateToNotesEdit(note.id)
    }

    fun onNoteDeleted(note: Note, position: Int) {
        job = GlobalScope.launch(dispatchersProvider.getMainDispatcher()) {
            try {
                withContext(dispatchersProvider.getIODispatcher()) {
                    deleteNote.execute(note)
                }
            } catch (ioException: IOException) {
                ioException.printStackTrace()
                return@launch
            }
            viewState.removeNoteAt(position)
        }
    }

    fun onNoteDuplicated(note: Note) {
        router.navigateToNotesEdit(note.id, true)
    }



}