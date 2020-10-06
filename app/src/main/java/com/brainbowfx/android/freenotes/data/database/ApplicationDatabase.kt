package com.brainbowfx.android.freenotes.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.brainbowfx.android.freenotes.data.database.dao.ImagesDao
import com.brainbowfx.android.freenotes.data.database.dao.NotesDao
import com.brainbowfx.android.freenotes.data.database.models.ImageEntity
import com.brainbowfx.android.freenotes.data.database.models.NoteEntity

@Database(entities = [NoteEntity::class, ImageEntity::class], version = 1, exportSchema = false)
abstract class ApplicationDatabase : RoomDatabase() {
    abstract fun getNotesDao(): NotesDao
    abstract fun getImagesDao(): ImagesDao
}