package com.brainbowfx.android.freenotes.data.database.models.projections

import androidx.room.Embedded
import androidx.room.Relation
import com.brainbowfx.android.freenotes.data.database.models.ImageEntity
import com.brainbowfx.android.freenotes.data.database.models.NoteEntity

class NoteWithImages(
    @Embedded
    val noteEntity: NoteEntity,
    @Relation(
        parentColumn = "rowid",
        entityColumn = "note_id"
    )
    val images: List<ImageEntity>
)