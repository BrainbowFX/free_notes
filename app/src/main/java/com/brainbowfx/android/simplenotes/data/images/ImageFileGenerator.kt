package com.brainbowfx.android.simplenotes.data.images

import com.brainbowfx.android.simplenotes.di.scopes.Activity
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@Activity
class ImageFileGenerator @Inject constructor(private val externalFilesDir: File?,
                                                 private val simpleDateFormat: SimpleDateFormat) {

    @Throws(IOException::class)
    fun generateImageFile(): File? {
        val timeStamp = simpleDateFormat.format(Date())
        return File.createTempFile("JPEG_$timeStamp", ".jpg", externalFilesDir)
    }
}