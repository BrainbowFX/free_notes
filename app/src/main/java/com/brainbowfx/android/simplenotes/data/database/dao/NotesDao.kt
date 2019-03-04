package com.brainbowfx.android.simplenotes.data.database.dao

import androidx.room.*
import com.brainbowfx.android.simplenotes.data.database.models.NoteEntity

@Dao
interface NotesDao {

    @Insert
    fun insert(note: NoteEntity)

    @Delete
    fun delete(note: NoteEntity)

    @Query("DELETE FROM notes WHERE `rowid` in (:notesIds)")
    fun delete(notesIds: IntArray)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(note: NoteEntity)

    @Query("SELECT * FROM notes")
    fun getAll(): List<NoteEntity>


}