package com.brainbowfx.android.freenotes

import com.brainbowfx.android.freenotes.di.DaggerTestComponentHolder
import com.brainbowfx.android.freenotes.presentation.presenter.ImagesPresenter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.mockito.Mockito
import java.io.IOException

class ImagesPresenterTest {

    private val imagesPresenter: ImagesPresenter = ImagesPresenter()

    @Before
    fun setUp() {
        DaggerTestComponentHolder.testComponent.inject(imagesPresenter)

        Mockito.`when`(imagesPresenter.coroutineDispatchersProvider.getIODispatcher()).thenReturn(Dispatchers.Default)
    }

    @Test
    fun `test createImageFile method when image file created succesfully should return valid url`() {
        runBlocking {
            Mockito.`when`(imagesPresenter.createImageFile.execute(Unit)).thenReturn("testUrl")
            val url = imagesPresenter.createImageFile()
            assertEquals("testUrl", url)
        }
    }

    @Test
    fun `test createImageFile method when CreateImageFile execute method throws exception should return empty string`() {
        runBlocking {
            Mockito.`when`(imagesPresenter.createImageFile.execute(Unit)).thenThrow(IOException())
            val url = imagesPresenter.createImageFile()
            assertEquals("", url)
        }
    }

    @Test
    fun `test takePhoto method when input argument is empty should return false`() {
        runBlocking {
            val result = imagesPresenter.takePhoto("")
            assertFalse(result)
        }
    }

    @Test
    fun `test takePhoto method when input url is correct should return true`() {
        runBlocking {
            val testUrl = "testUrl"
            Mockito.`when`(imagesPresenter.takePhoto.execute(testUrl)).thenReturn(true)
            val result = imagesPresenter.takePhoto(testUrl)
            assertTrue(result)
        }
    }
}
