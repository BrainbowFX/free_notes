package com.brainbowfx.android.freenotes.data.images

import com.brainbowfx.android.freenotes.di.scopes.Presenter
import com.brainbowfx.android.freenotes.domain.abstraction.ImageFileManager
import java.io.*
import javax.inject.Inject

@Presenter
class ImageFileManagerImpl @Inject constructor(
    private val externalFilesDir: File
) :
    ImageFileManager {

    @Throws(IOException::class)
    override fun addImageFile(): File? {
        return File.createTempFile( "note_", ".jpg", externalFilesDir)
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

    override fun copyImageFile(url: String): File? {
        val source = File(externalFilesDir, url.substringAfterLast("/"))
        val copy = addImageFile()

        return if (source.exists() && copy != null) {
            try {
                source.copyTo(copy, true)
            } catch (exception: IOException) {
                null
            }
        } else {
            null
        }
    }
}