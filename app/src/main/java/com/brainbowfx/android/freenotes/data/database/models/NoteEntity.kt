package com.brainbowfx.android.freenotes.data.database.models

import androidx.room.*

@Entity(tableName = "notes", indices = [Index("id", "title", "text")])
class NoteEntity(
    @PrimaryKey(autoGenerate = true)
    override val id: Long = 0,
    val title: String,
    val text: String,
    val date: Long,
    @ColumnInfo(name = "is_recycled")
    val isRecycled: Boolean = false,
) : BaseEntity