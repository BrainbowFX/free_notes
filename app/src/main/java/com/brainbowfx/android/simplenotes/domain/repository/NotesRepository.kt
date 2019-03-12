package com.brainbowfx.android.simplenotes.domain.repository

import com.brainbowfx.android.simplenotes.domain.entities.Note

interface NotesRepository {

    fun add(item: Note)

    fun delete(item: Note)

    fun delete(items: List<Note>)

    fun update(item: Note)

    fun getAll(): List<Note>
}