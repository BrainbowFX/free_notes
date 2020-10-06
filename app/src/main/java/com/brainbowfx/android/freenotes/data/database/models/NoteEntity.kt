package com.brainbowfx.android.freenotes.data.database.models

import androidx.room.*

@Fts4
@Entity(tableName = "notes")
class NoteEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "rowid")
    override var id: Long = 0,
    val title: String,
    val text: String,
    val date: Long,
    @ColumnInfo(name = "is_recycled")
    val isRecycled: Boolean = false,
) : BaseEntity