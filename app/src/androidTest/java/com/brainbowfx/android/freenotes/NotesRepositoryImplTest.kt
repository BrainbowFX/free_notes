package com.brainbowfx.android.freenotes

import androidx.room.Room
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.runner.AndroidJUnit4
import com.brainbowfx.android.freenotes.data.database.ApplicationDatabase
import com.brainbowfx.android.freenotes.data.database.NotesRepositoryImpl
import com.brainbowfx.android.freenotes.data.database.dao.ImagesDao
import com.brainbowfx.android.freenotes.data.database.dao.NotesDao
import com.brainbowfx.android.freenotes.data.database.models.ImageEntity
import com.brainbowfx.android.freenotes.data.database.models.NoteEntity
import com.brainbowfx.android.freenotes.data.database.models.projections.NoteWithImages
import com.brainbowfx.android.freenotes.domain.repository.NotesRepository
import com.brainbowfx.android.freenotes.presentation.App
import kotlinx.coroutines.runBlocking
import org.junit.*
import org.junit.runner.RunWith
import java.util.*

@RunWith(AndroidJUnit4ClassRunner::class)
class NotesRepositoryImplTest {

    private val db: ApplicationDatabase = Room.inMemoryDatabaseBuilder(
        App.Instance.applicationContext,
        ApplicationDatabase::class.java
    ).build()
    private val notesDao: NotesDao = db.getNotesDao()
    private val imagesDao: ImagesDao = db.getImagesDao()
    private val notesRepository: NotesRepository = NotesRepositoryImpl(notesDao, imagesDao, db)

    private fun createTestNotes(count: Int): List<NoteEntity> {
        val notes = mutableListOf<NoteEntity>()
        for (i in 0 until count) {
            notes.add(
                NoteEntity(
                    title = "title $i",
                    text = "content $i",
                    date = Date().time
                )
            )
        }
        return notes
    }

    @Test
    fun testGet() {
        runBlocking {
            val noteEntity = createTestNotes(1).first()
            val noteId = notesDao.insert(noteEntity)
            val imageEntity =
                ImageEntity(imageId = UUID.randomUUID().toString(), noteId = noteId, url = "url")
            imagesDao.insert(imageEntity)

            val noteWithImage = notesRepository.get(0)

            assert(noteWithImage != null)
            assert(noteWithImage?.noteEntity?.id == 1L)
            assert(noteWithImage?.images?.isNullOrEmpty() == false)
        }
    }

    @Test
    fun testDelete() {
        runBlocking {
            val entities = createTestNotes(5)
            notesDao.insert(entities)
            val deletedCount = notesRepository.delete(listOf(entities.first(), entities.last()))
            assert(deletedCount == 2)
        }
    }

    @Test
    fun testUpdate() {
        runBlocking {
            val changedContent = "changedContent"
            val entity = createTestNotes(1).first()
            notesDao.insert(entity)
            val updatedEntity = NoteEntity(1, "title", changedContent, Date().time)
            val images = listOf(
                ImageEntity(
                    id = 1,
                    imageId = UUID.randomUUID().toString(),
                    noteId = 0L,
                    url = "testUrl"
                )
            )
            val noteWithImages = NoteWithImages(updatedEntity, images)
            notesRepository.update(noteWithImages)

            val note = notesDao.getAll().first()
            val image = imagesDao.getAll().firstOrNull()

            assert(note.text == changedContent)
            assert(image != null)
            assert(image?.noteId == note.id)
        }
    }

    @Test
    fun testGetAll() {
        runBlocking {
            val notes = createTestNotes(5)
            notesDao.insert(notes)
            val image = ImageEntity(
                imageId = UUID.randomUUID().toString(),
                noteId = 1,
                url = "testUrl"
            )
            imagesDao.insert(image)

            val notesWithImages = notesRepository.getAll(false)
            assert(notesWithImages.size == 5)
            assert(notesWithImages.first().images.isNotEmpty())
            assert(notesWithImages[1].images.isEmpty())
        }
    }

    @Test
    fun testAdd() {
        runBlocking {
            val note = createTestNotes(1).first()
            val image = ImageEntity(
                noteId = 1,
                imageId = UUID.randomUUID().toString(),
                url = "testUrl"
            )
            val noteWithImages = NoteWithImages(note, listOf(image))

            notesRepository.add(noteWithImages)

            val noteEntity = notesDao.get(1)
            val imagesEntity = imagesDao.getAll()

            assert(noteEntity != null)
            assert(!imagesEntity.isNullOrEmpty())
        }
    }

    @After
    fun clear() {
        db.clearAllTables()
    }
}