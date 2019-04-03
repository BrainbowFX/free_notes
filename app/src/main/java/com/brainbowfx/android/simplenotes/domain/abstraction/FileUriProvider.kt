package com.brainbowfx.android.simplenotes.domain.abstraction

import java.io.File

interface FileUriProvider {
    fun getUriForFile(file: File): String
}