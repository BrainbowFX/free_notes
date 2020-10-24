package com.brainbowfx.android.freenotes.domain.interactor

import com.brainbowfx.android.freenotes.domain.CoroutineDispatchersProvider
import com.brainbowfx.android.freenotes.domain.abstraction.FileUriProvider
import com.brainbowfx.android.freenotes.domain.abstraction.ImageFileManager
import kotlinx.coroutines.withContext
import java.io.FileNotFoundException
import java.io.IOException
import javax.inject.Inject

class CopyImageFile @Inject constructor(
    private val imageFileManager: ImageFileManager,
    private val fileUriProvider: FileUriProvider,
    private val coroutineDispatchersProvider: CoroutineDispatchersProvider
) : UseCase<String, String?>() {

    @Throws(FileNotFoundException::class)
    override suspend fun execute(url: String): String? =
        withContext(coroutineDispatchersProvider.getIODispatcher()) {
            imageFileManager.copyImageFile(url)?.let {
                fileUriProvider.getUriForFile(it)
            }
        }
}