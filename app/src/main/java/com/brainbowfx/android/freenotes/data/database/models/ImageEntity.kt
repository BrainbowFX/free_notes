package com.brainbowfx.android.freenotes.data.database.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "images")
data class ImageEntity(
    @PrimaryKey(autoGenerate = true)
    override val id: Long = 0,
    @ColumnInfo(name = "note_id")
    val noteId: Long,
    val url: String
) : BaseEntity