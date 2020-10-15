package com.brainbowfx.android.freenotes

import com.brainbowfx.android.freenotes.di.DaggerTestComponentHolder
import com.brainbowfx.android.freenotes.domain.CoroutineDispatchersProvider
import com.brainbowfx.android.freenotes.domain.abstraction.FileUriProvider
import com.brainbowfx.android.freenotes.domain.abstraction.ImageFileManager
import com.brainbowfx.android.freenotes.domain.entities.Image
import com.brainbowfx.android.freenotes.domain.interactor.CreateImageFile
import com.brainbowfx.android.freenotes.domain.interactor.DeleteImages
import com.brainbowfx.android.freenotes.domain.repository.ImagesRepository
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.anyArray
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Test
import org.mockito.Mockito
import javax.inject.Inject
import org.junit.Assert.*
import org.junit.Rule
import org.junit.rules.TemporaryFolder
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import java.io.IOException

@RunWith(MockitoJUnitRunner::class)
class DeleteImagesTest {

    @Inject
    lateinit var imagesRepository: ImagesRepository

    @Inject
    lateinit var imageFileManager: ImageFileManager

    private var deleteImages: DeleteImages

    @get:Rule
    var tempFolder: TemporaryFolder = TemporaryFolder()

    init {
        DaggerTestComponentHolder.testComponent.inject(this)
        deleteImages = DeleteImages(imagesRepository, imageFileManager)
    }

    @Test
    fun `test execute when images count 2 should call imageFileManager's deleteImageFile twice`() {
        val testImages = listOf(
            Image(id = 1, url = "image_1"),
            Image(id = 2, url = "image_2")
        )
        runBlocking {
            Mockito.`when`(imagesRepository.deleteByIds(testImages.map { it.id }.toLongArray())).thenReturn(2)
            Mockito.`when`(imageFileManager.deleteImageFile(any())).thenReturn(true)
            deleteImages.execute(testImages)
            verify(imageFileManager, times(2)).deleteImageFile(any())
        }
    }

    @After
    fun clear() {
        Mockito.reset(imagesRepository, imageFileManager)
    }
}