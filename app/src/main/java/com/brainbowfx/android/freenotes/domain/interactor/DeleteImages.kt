package com.brainbowfx.android.freenotes.domain.interactor

import com.brainbowfx.android.freenotes.domain.abstraction.ImageFileManager
import com.brainbowfx.android.freenotes.domain.entities.Image
import com.brainbowfx.android.freenotes.domain.repository.ImagesRepository
import javax.inject.Inject

class DeleteImages @Inject constructor(
    private val imagesRepository: ImagesRepository,
    private val imageFileManager: ImageFileManager
) : UseCase<List<Image>, Unit>() {
    override suspend fun execute(images: List<Image>) {
        imagesRepository.deleteByIds(images.map { it.id }.toLongArray())
        images.forEach {
            imageFileManager.deleteImageFile(it.url)
        }
    }
}