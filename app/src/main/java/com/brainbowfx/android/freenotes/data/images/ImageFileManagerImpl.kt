package com.brainbowfx.android.freenotes.data.images

import com.brainbowfx.android.freenotes.TIMESTAMP_NAMED_ID
import com.brainbowfx.android.freenotes.di.scopes.Presenter
import com.brainbowfx.android.freenotes.domain.abstraction.ImageFileManager
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject
import javax.inject.Named

@Presenter
class ImageFileManagerImpl @Inject constructor(
    private val externalFilesDir: File,
    @Named(TIMESTAMP_NAMED_ID) private val simpleDateFormat: SimpleDateFormat

) :
    ImageFileManager {

    @Throws(IOException::class)
    override fun addImageFile(): File? {
        val timeStamp = simpleDateFormat.format(Date())
         return File.createTempFile("JPEG_$timeStamp", ".jpg", externalFilesDir)
    }

    @Throws(IOException::class)
    override fun deleteImageFile(url: String): Boolean {
        val file = File(externalFilesDir, url.substringAfterLast("/"))

        return if (file.exists()) {
            file.delete()
        } else {
            false
        }
    }
}