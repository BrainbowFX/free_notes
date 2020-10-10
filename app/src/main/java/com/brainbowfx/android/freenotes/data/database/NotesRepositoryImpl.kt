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
    private val forwardMapper: Mapper<Note, NoteEntity>,
    private val backwardMapper: Mapper<NoteWithImages, Note>,
    private val imagesForwardMapper: Mapper<Image, ImageEntity>,
    private val notesDao: NotesDao,
    private val imagesDao: ImagesDao,
    private val applicationDatabase: ApplicationDatabase
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

    override suspend fun update(item: Note) = applicationDatabase.withTransaction {
        notesDao.update(forwardMapper.map(item))
        if (item.images.isEmpty()) return@withTransaction
        val imagesIds: MutableList<Long> = mutableListOf()
        val images = item.images.map {
            imagesIds.add(it.id)
            it.noteId = item.id
            imagesForwardMapper.map(it)
        }
        imagesDao.insert(images)
    }

    override suspend fun getAll(recycled: Boolean): List<Note> =
        notesDao.getNoteWithImages(recycled).map { backwardMapper.map(it) }


    override suspend fun add(item: Note): Long = applicationDatabase.withTransaction {
        val note = forwardMapper.map(item)
        val noteId = notesDao.insert(note)
        val images = item.images
            .map {
                it.noteId = noteId
                imagesForwardMapper.map(it)
            }

        if (images.isNotEmpty()) {
            imagesDao.insert(images)
        }
        noteId
    }

}