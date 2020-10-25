package com.brainbowfx.android.freenotes.data.database

import androidx.room.withTransaction
import com.brainbowfx.android.freenotes.data.database.dao.ImagesDao
import com.brainbowfx.android.freenotes.data.database.dao.NotesDao
import com.brainbowfx.android.freenotes.data.database.models.ImageEntity
import com.brainbowfx.android.freenotes.data.database.models.NoteEntity
import com.brainbowfx.android.freenotes.data.database.models.projections.NoteWithImages
import com.brainbowfx.android.freenotes.domain.repository.NotesRepository
import com.brainbowfx.android.freenotes.di.scopes.Presenter
import com.brainbowfx.android.freenotes.domain.entities.Image
import com.brainbowfx.android.freenotes.domain.entities.Note
import com.brainbowfx.android.freenotes.domain.mappers.Mapper
import javax.inject.Inject

@Presenter
class NotesRepositoryImpl @Inject constructor(
    private val notesDao: NotesDao,
    private val imagesDao: ImagesDao,
    private val applicationDatabase: ApplicationDatabase
) : NotesRepository {

    override suspend fun get(id: Long): NoteWithImages? = notesDao.getNoteWithImages(id)

    override suspend fun delete(item: NoteEntity) {
        notesDao.delete(item)
    }

    override suspend fun delete(items: List<NoteEntity>): Int {
        val itemsIds = items.map { it.id }.toLongArray()
        return notesDao.delete(itemsIds)
    }

    override suspend fun update(item: NoteWithImages) = applicationDatabase.withTransaction {
        notesDao.update(item.noteEntity)
        saveImages(item.images, item.noteEntity.id)
    }

    override suspend fun getAll(recycled: Boolean): List<NoteWithImages> =
        notesDao.getNoteWithImages(recycled)

    override suspend fun find(query: String): List<NoteEntity> = notesDao.find(query)

    override suspend fun add(item: NoteWithImages): Long = applicationDatabase.withTransaction {
        val noteId = notesDao.insert(item.noteEntity)
        saveImages(item.images, noteId)
        noteId
    }

    private suspend fun saveImages(images: List<ImageEntity>, noteId: Long) {
        if (images.isNotEmpty()) {
            images.forEach { it.noteId = noteId }
            imagesDao.insert(images)
        }
    }

}