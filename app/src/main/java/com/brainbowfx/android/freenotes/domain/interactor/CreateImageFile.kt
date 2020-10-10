package com.brainbowfx.android.freenotes.domain.interactor

import com.brainbowfx.android.freenotes.domain.CoroutineDispatchersProvider
import com.brainbowfx.android.freenotes.domain.abstraction.FileUriProvider
import com.brainbowfx.android.freenotes.domain.abstraction.ImageFileManager
import kotlinx.coroutines.withContext
import java.io.IOException
import javax.inject.Inject

class CreateImageFile @Inject constructor(
    private val imageFileManager: ImageFileManager,
    private val fileUriProvider: FileUriProvider,
    private val coroutineDispatchersProvider: CoroutineDispatchersProvider
) : UseCase<Unit, String?>() {

    @Throws(IOException::class)
    override suspend fun execute(params: Unit): String? =
        withContext(coroutineDispatchersProvider.getIODispatcher()) {
            imageFileManager.addImageFile()?.let {
                fileUriProvider.getUriForFile(it)
            }
        }
}