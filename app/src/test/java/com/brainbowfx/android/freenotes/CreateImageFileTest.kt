package com.brainbowfx.android.freenotes

import com.brainbowfx.android.freenotes.di.DaggerTestComponentHolder
import com.brainbowfx.android.freenotes.domain.CoroutineDispatchersProvider
import com.brainbowfx.android.freenotes.domain.abstraction.FileUriProvider
import com.brainbowfx.android.freenotes.domain.abstraction.ImageFileManager
import com.brainbowfx.android.freenotes.domain.interactor.CreateImageFile
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
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
class CreateImageFileTest {

    @Inject
    lateinit var imageFileManager: ImageFileManager

    @Inject
    lateinit var fileUriProvider: FileUriProvider

    @Inject
    lateinit var coroutineDispatchersProvider: CoroutineDispatchersProvider

    private var createImageFile: CreateImageFile

    @get:Rule
    var tempFolder: TemporaryFolder = TemporaryFolder()

    init {
        DaggerTestComponentHolder.testComponent.inject(this)
        createImageFile = CreateImageFile(imageFileManager, fileUriProvider, coroutineDispatchersProvider)
        Mockito.`when`(coroutineDispatchersProvider.getIODispatcher()).thenReturn(Dispatchers.Unconfined)
    }

    @Test
    fun `test execute method when imageFileGenerator's generateImageFile method returns null should return null`(){
        Mockito.`when`(imageFileManager.addImageFile()).thenReturn(null)

        runBlocking {
            val uri = createImageFile.execute(Unit)
            assertNull(uri)
        }
    }

    @Test
    fun `test execute method when imageFileGenerator's generateImageFile method returns file should return url string`(){
        val filename = "tempFile"
        val testFile = tempFolder.newFile(filename)
        Mockito.`when`(imageFileManager.addImageFile()).thenReturn(testFile)
        Mockito.`when`(fileUriProvider.getUriForFile(testFile)).thenReturn(filename)
        runBlocking {
            val uri = createImageFile.execute(Unit)
            assertEquals(filename, uri)
        }
    }

    @Test
    fun `execute method when imageFileGenerator's generateImageFile method throws IOException should throw IOException`(){
        Mockito.doAnswer{ throw IOException() }.`when`(imageFileManager).addImageFile()
        runBlocking {
            try {
                createImageFile.execute(Unit)
            } catch (ioException: IOException) {
                assert(true)
            }
        }
    }
}