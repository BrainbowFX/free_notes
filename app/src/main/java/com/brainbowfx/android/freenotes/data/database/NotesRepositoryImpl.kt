package com.brainbowfx.android.freenotes.data.database

import com.brainbowfx.android.freenotes.data.database.dao.NotesDao
import com.brainbowfx.android.freenotes.data.database.models.NoteEntity
import com.brainbowfx.android.freenotes.domain.repository.NotesRepository
import com.brainbowfx.android.freenotes.di.scopes.Activity
import com.brainbowfx.android.freenotes.di.scopes.ActivityPerInstance
import com.brainbowfx.android.freenotes.domain.entities.Note
import com.brainbowfx.android.freenotes.domain.mappers.Mapper
import javax.inject.Inject

@ActivityPerInstance
class NotesRepositoryImpl @Inject constructor(
    private val forwardMapper: Mapper<Note, NoteEntity>,
    private val backwardMapper: Mapper<NoteEntity, Note>,
    private val dao: NotesDao) : NotesRepository {

    override fun get(id: Long): Note {
        val noteEntity = dao.get(id)
        return backwardMapper.map(noteEntity)
    }

    override fun delete(item: Note) {
        val noteEntity = forwardMapper.map(item)
        dao.delete(noteEntity)
    }

    override fun delete(items: List<Note>) {
        val itemsIds = items.map { it.id }.toLongArray()
        dao.delete(itemsIds)
    }

    override fun update(item: Note) {
        dao.update(forwardMapper.map(item))
    }

    override fun getAll(): List<Note> = dao.getAll().map { backwardMapper.map(it) }

    override fun add(item: Note): Long = dao.insert(forwardMapper.map(item))
}