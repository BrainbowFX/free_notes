package com.brainbowfx.android.simplenotes.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.brainbowfx.android.simplenotes.data.database.dao.NotesDao
import com.brainbowfx.android.simplenotes.data.database.models.NoteEntity
import com.brainbowfx.android.simplenotes.data.mappers.Converters

@Database(entities = [NoteEntity::class], version = 1)
@TypeConverters(Converters::class)
abstract class ApplicationDatabase : RoomDatabase() {
    abstract fun getNotesDao(): NotesDao
}