package com.brainbowfx.android.freenotes.domain.entities

import java.util.*

data class Image(
    val id: Long = 0,
    var noteId: Long = 0,
    val imageId: String = UUID.randomUUID().toString(),
    val url: String
)