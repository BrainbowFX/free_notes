package com.brainbowfx.android.freenotes.data.database

import androidx.room.Transaction
import com.brainbowfx.android.freenotes.data.database.dao.ImagesDao
import com.brainbowfx.android.freenotes.data.database.dao.NotesDao
import com.brainbowfx.android.freenotes.data.database.models.ImageEntity
import com.brainbowfx.android.freenotes.data.database.models.NoteEntity
import com.brainbowfx.android.freenotes.data.database.models.projections.NoteWithImages
import com.brainbowfx.android.freenotes.domain.repository.NotesRepository
import com.brainbowfx.android.freenotes.di.scopes.ActivityPerInstance
import com.brainbowfx.android.freenotes.domain.entities.Image
import com.brainbowfx.android.freenotes.domain.entities.Note
import com.brainbowfx.android.freenotes.domain.mappers.Mapper
import javax.inject.Inject

@ActivityPerInstance
class NotesRepositoryImpl @Inject constructor(
    private val forwardMapper: Mapper<Note, NoteEntity>,
    private val backwardMapper: Mapper<NoteWithImages, Note>,
    private val imagesForwardMapper: Mapper<Image, ImageEntity>,
    private val notesDao: NotesDao,
    private val imagesDao: ImagesDao
) : NotesRepository {

    override suspend fun get(id: Long): Note {
        val notesWithImages = notesDao.getNoteWithImages(id)
        return backwardMapper.map(notesWithImages)
    }

    override suspend fun delete(item: Note) {
        val noteEntity = forwardMapper.map(item)
        notesDao.delete(noteEntity)
    }

    override suspend fun delete(items: List<Note>) {
        val itemsIds = items.map { it.id }.toLongArray()
        notesDao.delete(itemsIds)
    }

    @Transaction
    override suspend fun update(item: Note) {
        notesDao.update(forwardMapper.map(item))
        val imagesIds: MutableList<Long> = mutableListOf()
        val images = item.images.map {
            imagesIds.add(it.id)
            imagesForwardMapper.map(it)
        }
        imagesDao.update(images)
        imagesDao.deleteNotUpdated(imagesIds.toLongArray())
    }

    override suspend fun getAll(recycled: Boolean): List<Note> = notesDao.getNoteWithImages(recycled).map { backwardMapper.map(it) }

    @Transaction
    override suspend fun add(item: Note): Long {
        val note = forwardMapper.map(item)
        val noteId = notesDao.insert(note)
        val images = item.images.map(imagesForwardMapper::map)
        if (images.isNotEmpty()) {
            imagesDao.insert(images)
        }
        return noteId
    }
}