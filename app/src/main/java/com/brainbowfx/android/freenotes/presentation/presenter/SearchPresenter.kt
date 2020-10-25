package com.brainbowfx.android.freenotes.presentation.presenter

import com.arellomobile.mvp.InjectViewState
import com.brainbowfx.android.freenotes.domain.interactor.Search
import com.brainbowfx.android.freenotes.domain.router.NotesRouter
import com.brainbowfx.android.freenotes.domain.router.Router
import com.brainbowfx.android.freenotes.presentation.view.contract.SearchView
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@InjectViewState
class SearchPresenter : ScopedPresenter<SearchView>() {

    @Inject
    lateinit var search: Search

    @Inject
    lateinit var searchRouter: Router

    @Inject
    lateinit var notesRouter: NotesRouter

    companion object {
        const val SEARCH_DEBOUNCE_DELAY = 500L
    }

    private var searchJob: Job? = null

    fun onStart() {
        viewState.setupButton()
    }

    fun onQueryChanged(query: String) {
        searchJob?.cancel()
        searchJob = launch {
            delay(SEARCH_DEBOUNCE_DELAY)
            val notes = search.execute(query)
            viewState.setResult(query, notes)
        }
    }

    fun onNoteSelected(noteId: Long) {
        notesRouter.navigateNext(noteId)
    }

    fun onFloatingButtonClick() {
        searchRouter.returnBack()
    }
}