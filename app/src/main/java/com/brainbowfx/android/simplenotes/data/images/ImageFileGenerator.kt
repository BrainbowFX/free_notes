package com.brainbowfx.android.simplenotes.data.images

import com.brainbowfx.android.simplenotes.di.scopes.Activity
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject
import javax.inject.Named

@Activity
class ImageFileGenerator @Inject constructor() {
    @Inject
    lateinit var externalFilesDir: File

    @field:[Inject Named("TimeStamp")]
    lateinit var simpleDateFormat: SimpleDateFormat

    @Throws(IOException::class)
    fun generateImageFile(): File? {
        val timeStamp = simpleDateFormat.format(Date())
        return File.createTempFile("JPEG_$timeStamp", ".jpg", externalFilesDir)
    }
}