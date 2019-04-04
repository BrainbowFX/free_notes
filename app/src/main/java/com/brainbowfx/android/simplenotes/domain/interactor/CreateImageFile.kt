package com.brainbowfx.android.simplenotes.domain.interactor

import com.brainbowfx.android.simplenotes.domain.abstraction.FileUriProvider
import com.brainbowfx.android.simplenotes.domain.abstraction.ImageFileGenerator
import java.io.IOException
import javax.inject.Inject

class CreateImageFile @Inject constructor(
    val imageFileGenerator: ImageFileGenerator,
    val fileUriProvider: FileUriProvider
) : UseCase<Unit, String>() {

    @Throws(IOException::class)
    override suspend fun execute(params: Unit): String {
        val file = imageFileGenerator.generateImageFile()
        return if (file != null) fileUriProvider.getUriForFile(file) else ""
    }

}