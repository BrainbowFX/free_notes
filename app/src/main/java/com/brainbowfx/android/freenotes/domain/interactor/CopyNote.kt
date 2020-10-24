package com.brainbowfx.android.freenotes.domain.interactor

import com.brainbowfx.android.freenotes.domain.CoroutineDispatchersProvider
import com.brainbowfx.android.freenotes.domain.abstraction.FileUriProvider
import com.brainbowfx.android.freenotes.domain.abstraction.ImageFileManager
import com.brainbowfx.android.freenotes.domain.entities.Image
import com.brainbowfx.android.freenotes.domain.entities.Note
import kotlinx.coroutines.withContext
import java.io.IOException
import javax.inject.Inject

class CopyNote @Inject constructor(
    private val copyImageFile: CopyImageFile,
    private val coroutineDispatchersProvider: CoroutineDispatchersProvider
) : UseCase<Note, Note>() {

    @Throws(IOException::class)
    override suspend fun execute(note: Note): Note =
        withContext(coroutineDispatchersProvider.getIODispatcher()) {
            note.copy(
                id = 0,
                title = note.title,
                text = note.text,
                dateTime = note.dateTime
            ).apply {
                if (!note.images.isNullOrEmpty()) {
                    images = note.images
                        .mapNotNull { copyImageFile.execute(it.url) }
                        .map { Image(url = it) }
                        .toMutableList()
                }
            }
        }
}