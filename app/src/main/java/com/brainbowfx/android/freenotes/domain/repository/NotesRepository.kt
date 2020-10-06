package com.brainbowfx.android.freenotes.domain.repository

import com.brainbowfx.android.freenotes.domain.entities.Note

interface NotesRepository {

    suspend fun add(item: Note): Long

    suspend fun delete(item: Note)

    suspend fun delete(items: List<Note>)

    suspend fun update(item: Note)

    suspend fun get(id: Long): Note

    suspend fun getAll(recycled: Boolean): List<Note>
}