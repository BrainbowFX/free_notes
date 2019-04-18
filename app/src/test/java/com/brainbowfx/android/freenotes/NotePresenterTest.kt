package com.brainbowfx.android.freenotes

import com.brainbowfx.android.freenotes.di.DaggerTestComponentHolder
import com.brainbowfx.android.freenotes.presentation.presenter.NotePresenter
import com.brainbowfx.android.freenotes.presentation.view.contract.`NotesEditView$$State`
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

class NotePresenterTest {

    private val notePresenter = NotePresenter(-1L, false)

    @ExperimentalCoroutinesApi
    @Before
    fun setup() {
        DaggerTestComponentHolder.testComponent.inject(notePresenter)

        val viewState = Mockito.mock(`NotesEditView$$State`::class.java)
        notePresenter.setViewState(viewState)
    }

    @Test
    fun `test onImageSelectionChanged when items count more than zero should trigger showDeleteButton of viewstate`() {
        notePresenter.onImagesSelectionChanged(2)
        verify(notePresenter.viewState).showDeleteButton()
    }

    @Test
    fun `test onImageSelectionChanged when items count less than zero or equals should trigger hideDeleteButton of viewstate`() {
        notePresenter.onImagesSelectionChanged(0)
        verify(notePresenter.viewState).hideDeleteButton()
    }

}