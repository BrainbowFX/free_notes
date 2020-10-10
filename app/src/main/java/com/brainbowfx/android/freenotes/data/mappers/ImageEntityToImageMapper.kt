package com.brainbowfx.android.freenotes.data.mappers

import com.brainbowfx.android.freenotes.data.database.models.ImageEntity
import com.brainbowfx.android.freenotes.domain.entities.Image
import com.brainbowfx.android.freenotes.domain.mappers.Mapper

class ImageEntityToImageMapper : Mapper<ImageEntity, Image> {
    override fun map(input: ImageEntity): Image = with(input) {
        Image(
            id = id,
            imageId = input.imageId,
            noteId = noteId,
            url = url
        )
    }
}