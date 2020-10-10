package com.brainbowfx.android.freenotes.domain.abstraction

import java.io.File

interface ImageFileManager {

    fun addImageFile(): File?

    fun deleteImageFile(url: String): Boolean
}