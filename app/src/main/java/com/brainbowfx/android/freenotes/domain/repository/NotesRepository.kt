package com.brainbowfx.android.freenotes.domain.repository

import com.brainbowfx.android.freenotes.data.database.models.NoteEntity
import com.brainbowfx.android.freenotes.data.database.models.projections.NoteWithImages

interface NotesRepository {

    suspend fun add(item: NoteWithImages): Long

    suspend fun delete(item: NoteEntity)

    suspend fun delete(items: List<NoteEntity>)

    suspend fun update(item: NoteWithImages)

    suspend fun get(id: Long): NoteWithImages?

    suspend fun getAll(recycled: Boolean): List<NoteWithImages>
}