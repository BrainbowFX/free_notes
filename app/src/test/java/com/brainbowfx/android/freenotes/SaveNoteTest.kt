package com.brainbowfx.android.freenotes

import com.brainbowfx.android.freenotes.data.database.models.projections.NoteWithImages
import com.brainbowfx.android.freenotes.di.DaggerTestComponentHolder
import com.brainbowfx.android.freenotes.domain.entities.Note
import com.brainbowfx.android.freenotes.domain.interactor.SaveNote
import com.brainbowfx.android.freenotes.domain.mappers.Mapper
import com.brainbowfx.android.freenotes.domain.repository.NotesRepository
import com.nhaarman.mockitokotlin2.any
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@RunWith(MockitoJUnitRunner::class)
class SaveNoteTest {

    @Inject
    lateinit var notesRepository: NotesRepository

    @Inject
    lateinit var mapper: Mapper<Note, NoteWithImages>

    @Inject
    lateinit var simpleDateFormat: SimpleDateFormat

    private val saveNote: SaveNote

    init {
        DaggerTestComponentHolder.testComponent.inject(this)
        saveNote = SaveNote(notesRepository, mapper)
    }

    @Before
    fun prepare() {
        runBlocking {
            Mockito.`when`(notesRepository.add(any())).thenReturn(1L)
            Mockito.`when`(notesRepository.update(any())).thenReturn(Unit)
        }
    }

    @Test
    fun `test execute when note id is 0 should return 1`() {
        val note = Note(0, dateTime = simpleDateFormat.format(Date()))
        runBlocking {
            val id = saveNote.execute(note)
            assert(id == 1L)
        }
    }

    @Test
    fun `test execute when note id is 1 should return null`() {
        val note = Note(1, dateTime = simpleDateFormat.format(Date()))
        runBlocking {
            val id = saveNote.execute(note)
            assert(id == null)
        }
    }
}