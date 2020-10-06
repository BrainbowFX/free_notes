package com.brainbowfx.android.freenotes.data.mappers

import com.brainbowfx.android.freenotes.data.database.models.ImageEntity
import com.brainbowfx.android.freenotes.domain.entities.Image
import com.brainbowfx.android.freenotes.domain.mappers.Mapper

class ImageToImageEntityMapper : Mapper<Image, ImageEntity> {
    override fun map(input: Image): ImageEntity = with(input) {
        ImageEntity(
            id = id,
            noteId = noteId,
            url = url
        )
    }
}