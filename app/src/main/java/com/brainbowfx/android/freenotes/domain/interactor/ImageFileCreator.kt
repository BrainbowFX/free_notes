package com.brainbowfx.android.freenotes.domain.interactor

import com.brainbowfx.android.freenotes.domain.CoroutineDispatchersProvider
import com.brainbowfx.android.freenotes.domain.abstraction.FileUriProvider
import com.brainbowfx.android.freenotes.domain.abstraction.ImageFileGenerator
import kotlinx.coroutines.withContext
import java.io.IOException
import javax.inject.Inject

class ImageFileCreator @Inject constructor(
    private val imageFileGenerator: ImageFileGenerator,
    private val fileUriProvider: FileUriProvider,
    private val coroutineDispatchersProvider: CoroutineDispatchersProvider
) : UseCase<Unit, String?>() {

    @Throws(IOException::class)
    override suspend fun execute(params: Unit): String? =
        withContext(coroutineDispatchersProvider.getIODispatcher()) {
            imageFileGenerator.generateImageFile()?.let {
                fileUriProvider.getUriForFile(it)
            }
        }
}