package com.brainbowfx.android.freenotes.domain.abstraction

import java.io.File

interface ImageFileGenerator {
    fun generateImageFile(): File?
}