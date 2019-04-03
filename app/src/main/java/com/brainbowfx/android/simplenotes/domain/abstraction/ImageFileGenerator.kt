package com.brainbowfx.android.simplenotes.domain.abstraction

import java.io.File

interface ImageFileGenerator {
    fun generateImageFile(): File?
}