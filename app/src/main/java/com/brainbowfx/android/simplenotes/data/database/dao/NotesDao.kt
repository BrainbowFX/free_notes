package com.brainbowfx.android.simplenotes.data.database.dao

import androidx.room.*
import com.brainbowfx.android.simplenotes.data.database.models.NoteEntity

@Dao
interface NotesDao {

    @Insert
    fun insert(note: NoteEntity): Long

    @Delete
    fun delete(note: NoteEntity)

    @Query("DELETE FROM notes WHERE `rowid` in (:notesIds)")
    fun delete(notesIds: LongArray)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(note: NoteEntity)

    @Query("SELECT *, `rowid` FROM notes WHERE `rowid`=(:noteId)")
    fun get(noteId: Long): NoteEntity

    @Query("SELECT *, `rowid` FROM notes")
    fun getAll(): List<NoteEntity>


}