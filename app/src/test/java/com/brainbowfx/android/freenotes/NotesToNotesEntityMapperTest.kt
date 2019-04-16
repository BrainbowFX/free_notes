package com.brainbowfx.android.freenotes

import com.brainbowfx.android.freenotes.data.mappers.NotesToNotesEntityMapper
import com.brainbowfx.android.freenotes.domain.entities.Note
import org.junit.Before
import org.junit.Test
import java.text.SimpleDateFormat
import java.util.*
import org.junit.Assert.*

class NotesToNotesEntityMapperTest {

    private lateinit var notesToNotesEntityMapper: NotesToNotesEntityMapper
    private val simpleDateFormat: SimpleDateFormat = SimpleDateFormat(TIMESTAMP, Locale.getDefault())
    private val note = Note(dateTime = simpleDateFormat.format(Date()))

    @Before
    fun setup(){
        notesToNotesEntityMapper = NotesToNotesEntityMapper(simpleDateFormat)
    }

    @Test
    fun `test map method when input note id is -1 should return NoteEntity with id equals 0`(){
        note.id = -1
        val noteEntity = notesToNotesEntityMapper.map(note)
        assertEquals(0, noteEntity.id)
    }

    @Test
    fun `test map method when input note id is 42 should return NoteEntity with id equals 42`(){
        val noteId = 42L
        note.id = noteId
        val noteEntity = notesToNotesEntityMapper.map(note)
        assertEquals(noteId, noteEntity.id)
    }
}