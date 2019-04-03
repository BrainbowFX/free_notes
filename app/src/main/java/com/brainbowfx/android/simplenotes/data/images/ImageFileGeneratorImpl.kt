package com.brainbowfx.android.simplenotes.data.images

import com.brainbowfx.android.simplenotes.di.scopes.Activity
import com.brainbowfx.android.simplenotes.domain.abstraction.ImageFileGenerator
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject
import javax.inject.Named

@Activity
class ImageFileGeneratorImpl @Inject constructor() :
    ImageFileGenerator {

    @Inject
    lateinit var externalFilesDir: File

    @field:[Inject Named("TimeStamp")]
    lateinit var simpleDateFormat: SimpleDateFormat

    @Throws(IOException::class)
    override fun generateImageFile(): File? {
        val timeStamp = simpleDateFormat.format(Date())
         return File.createTempFile("JPEG_$timeStamp", ".jpg", externalFilesDir)
    }
}