package com.brainbowfx.android.freenotes.data.images

import com.brainbowfx.android.freenotes.TIMESTAMP_NAMED_ID
import com.brainbowfx.android.freenotes.di.scopes.Activity
import com.brainbowfx.android.freenotes.di.scopes.ActivityPerInstance
import com.brainbowfx.android.freenotes.domain.abstraction.ImageFileGenerator
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject
import javax.inject.Named

@ActivityPerInstance
class ImageFileGeneratorImpl @Inject constructor(
    private val externalFilesDir: File,
    @Named(TIMESTAMP_NAMED_ID) private val simpleDateFormat: SimpleDateFormat

) :
    ImageFileGenerator {

    @Throws(IOException::class)
    override fun generateImageFile(): File? {
        val timeStamp = simpleDateFormat.format(Date())
         return File.createTempFile("JPEG_$timeStamp", ".jpg", externalFilesDir)
    }
}