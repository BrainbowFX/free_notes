package com.brainbowfx.android.freenotes.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.brainbowfx.android.freenotes.data.database.dao.NotesDao
import com.brainbowfx.android.freenotes.data.database.models.NoteEntity
import com.brainbowfx.android.freenotes.data.mappers.Converters

@Database(entities = [NoteEntity::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class ApplicationDatabase : RoomDatabase() {
    abstract fun getNotesDao(): NotesDao
}