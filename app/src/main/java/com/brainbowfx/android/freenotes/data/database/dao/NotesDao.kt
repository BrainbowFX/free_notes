package com.brainbowfx.android.freenotes.data.database.dao

import androidx.room.*
import com.brainbowfx.android.freenotes.data.database.models.NoteEntity
import com.brainbowfx.android.freenotes.data.database.models.projections.NoteWithImages

@Dao
interface NotesDao : BaseDao<NoteEntity> {

    @Query("SELECT * FROM notes WHERE id = :noteId AND is_recycled = :recycled")
    suspend fun get(noteId: Long, recycled: Boolean = false): NoteEntity?

    @Transaction
    @Query("DELETE FROM notes WHERE id in (:noteIds)")
    suspend fun delete(noteIds: LongArray): Int

    @Transaction
    @Query("SELECT * FROM notes WHERE id = :noteId AND is_recycled = :recycled")
    suspend fun getNoteWithImages(noteId: Long, recycled: Boolean = false): NoteWithImages

    @Transaction
    @Query("SELECT * FROM notes WHERE is_recycled = :recycled")
    suspend fun getNoteWithImages(recycled: Boolean = false): List<NoteWithImages>

    @Query("SELECT * FROM notes")
    suspend fun getAll(): List<NoteEntity>

    @Query("SELECT * FROM notes where (title LIKE '%' || :query || '%' OR text LIKE '%' || :query || '%') AND NULLIF(:query, '') IS NOT NULL ")
    suspend fun find(query: String): List<NoteEntity>


}