package com.brainbowfx.android.freenotes

import com.brainbowfx.android.freenotes.di.DaggerTestComponentHolder
import com.brainbowfx.android.freenotes.domain.entities.Note
import com.brainbowfx.android.freenotes.presentation.presenter.NotePresenter
import com.brainbowfx.android.freenotes.presentation.view.contract.`NotesEditView$$State`
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

class NotePresenterTest {

    private val notePresenter = NotePresenter(-1L, false)

    @Before
    fun setup() {
        DaggerTestComponentHolder.testComponent.inject(notePresenter)

        val viewState = Mockito.mock(`NotesEditView$$State`::class.java)
        notePresenter.setViewState(viewState)

        Mockito.`when`(notePresenter.dispatchersProvider.getIODispatcher()).thenReturn(Dispatchers.Default)
    }

    @Test
    fun `test onImageSelectionChanged when items count more than zero should trigger showDeleteButton of viewstate`() {
        notePresenter.onImagesSelectionChanged(2)
        Mockito.verify(notePresenter.viewState).showDeleteButton()
    }

    @Test
    fun `test onImageSelectionChanged when items count less than zero or equals should trigger hideDeleteButton of viewstate`() {
        notePresenter.onImagesSelectionChanged(0)
        Mockito.verify(notePresenter.viewState).hideDeleteButton()
    }

    @Test
    fun `test prepareNote when input note id is -1 and duplicate is false should return new note with id different than -1`() {
        val note = Note()
        val duplicate = false
        val noteId = 12L

        runBlocking {
            Mockito.`when`(notePresenter.addNote.execute(note)).thenReturn(noteId)
            val resultNote = notePresenter.prepareNote(note, duplicate)
            assertNotEquals(-1, resultNote.id)
        }
    }

    @Test
    fun `test prepareNote when input note id is -1 and duplicate is true should return new note with id different than -1`() {
        val note = Note()
        val duplicate = true
        val noteId = 12L

        runBlocking {
            Mockito.`when`(notePresenter.addNote.execute(note)).thenReturn(noteId)
            val resultNote = notePresenter.prepareNote(note, duplicate)
            assertNotEquals(-1, resultNote.id)
        }
    }

    @Test
    fun `test prepareNote when input note id is 42 and duplicate is true should return note with id different than 42`() {
        val note = Note()
        val duplicate = true
        note.id = 42L

        runBlocking {
            Mockito.`when`(notePresenter.addNote.execute(note)).thenReturn(43L)
            val resultNote = notePresenter.prepareNote(note, duplicate)
            assertNotEquals(42L, resultNote.id)
        }
    }

    @Test
    fun `test prepareNote when input note id is 42 and duplicate is false should return note with id that equal 42`() {
        val note = Note()
        val duplicate = false
        val noteId = 42L
        note.id = noteId

        runBlocking {
            Mockito.`when`(notePresenter.addNote.execute(note)).thenReturn(43L)
            val resultNote = notePresenter.prepareNote(note, duplicate)
            assertEquals(noteId, resultNote.id)
        }

    }

    @Test
    fun `test getNote when input id is -1 should return Note with id that equals -1`() {
        val noteId = -1L
        runBlocking {
            val resultNote = notePresenter.getNote(noteId)
            assertEquals(noteId, resultNote.id)
        }
    }

    @Test
    fun `test getNote when input id is 25 should return Note with id that equals 25`() {
        val noteId = 25L

        runBlocking {
            Mockito.`when`(notePresenter.getNote.execute(noteId)).thenReturn(Note().also { it.id = noteId })
            val resultNote = notePresenter.getNote(noteId)
            assertEquals(noteId, resultNote.id)
        }
    }

}