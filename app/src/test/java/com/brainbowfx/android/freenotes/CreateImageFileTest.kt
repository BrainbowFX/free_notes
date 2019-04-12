package com.brainbowfx.android.freenotes

import com.brainbowfx.android.freenotes.di.DaggerTestComponentHolder
import com.brainbowfx.android.freenotes.domain.abstraction.FileUriProvider
import com.brainbowfx.android.freenotes.domain.abstraction.ImageFileGenerator
import com.brainbowfx.android.freenotes.domain.interactor.CreateImageFile
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import javax.inject.Inject
import org.junit.Assert.*
import org.junit.Rule
import org.junit.rules.TemporaryFolder
import java.io.IOException

class CreateImageFileTest {

    @Inject
    lateinit var imageFileGenerator: ImageFileGenerator

    @Inject
    lateinit var fileUriProvider: FileUriProvider

    private lateinit var createImageFile: CreateImageFile

    @get:Rule
    var tempFolder: TemporaryFolder = TemporaryFolder()

    @Before
    fun setup(){
        DaggerTestComponentHolder.testComponent.inject(this)
        createImageFile = CreateImageFile(imageFileGenerator, fileUriProvider)
    }

    @Test
    fun `test execute method when imageFileGenerator's generateImageFile method returns null should return empty string`(){
        Mockito.`when`(createImageFile.imageFileGenerator.generateImageFile()).thenReturn(null)

        runBlocking {
            val uri = createImageFile.execute(Unit)
            assertEquals("", uri)
        }
    }

    @Test
    fun `test execute method when imageFileGenerator's generateImageFile method returns file should return url string`(){
        val filename = "tempfile"
        val testfile = tempFolder.newFile(filename)
        Mockito.`when`(createImageFile.imageFileGenerator.generateImageFile()).thenReturn(testfile)
        Mockito.`when`(createImageFile.fileUriProvider.getUriForFile(testfile)).thenReturn(filename)
        runBlocking {
            val uri = createImageFile.execute(Unit)
            assertEquals(filename, uri)
        }
    }

    @Test
    fun `execute method when imageFileGenerator's generateImageFile method throws IOException should throw IOException`(){
        Mockito.doAnswer{ throw IOException() }.`when`(createImageFile.imageFileGenerator).generateImageFile()
        runBlocking {
            try {
                createImageFile.execute(Unit)
            } catch (ioException: IOException) {
                assert(true)
            }
        }
    }
}