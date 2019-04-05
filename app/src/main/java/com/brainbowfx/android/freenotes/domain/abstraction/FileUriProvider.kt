package com.brainbowfx.android.freenotes.domain.abstraction

import java.io.File

interface FileUriProvider {
    fun getUriForFile(file: File): String
}