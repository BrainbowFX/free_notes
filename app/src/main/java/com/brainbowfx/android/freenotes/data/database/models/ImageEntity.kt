package com.brainbowfx.android.freenotes.data.database.models

import androidx.room.*
import java.util.*

@Entity(
    tableName = "images",
    indices = [Index("note_id")],
    foreignKeys = [
        ForeignKey(
            entity = NoteEntity::class,
            parentColumns = ["id"],
            childColumns = ["note_id"],
            onDelete = ForeignKey.CASCADE
        )]
)
data class ImageEntity(
    @PrimaryKey(autoGenerate = true)
    override val id: Long = 0,
    @ColumnInfo(name = "image_id")
    val imageId: String,
    @ColumnInfo(name = "note_id")
    val noteId: Long,
    val url: String
) : BaseEntity