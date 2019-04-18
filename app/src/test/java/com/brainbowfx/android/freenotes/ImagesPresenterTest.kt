package com.brainbowfx.android.freenotes

import com.brainbowfx.android.freenotes.di.DaggerTestComponentHolder
import com.brainbowfx.android.freenotes.presentation.PERMISSION_WRITE_EXTERNAL_STORAGE
import com.brainbowfx.android.freenotes.presentation.presenter.ImagesPresenter
import com.brainbowfx.android.freenotes.presentation.view.contract.`ImagesView$$State`
import com.nhaarman.mockitokotlin2.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Test

import org.junit.Before
import org.mockito.Mockito
import java.io.IOException

class ImagesPresenterTest {

    private val imagesPresenter: ImagesPresenter = ImagesPresenter()
    private val argumentCaptor = argumentCaptor<((String)-> Unit)>()

    @Before
    fun setUp() {
        DaggerTestComponentHolder.testComponent.inject(imagesPresenter)
        Mockito.`when`(imagesPresenter.coroutineDispatchersProvider.getIODispatcher()).thenReturn(Dispatchers.Unconfined)
        Mockito.`when`(imagesPresenter.coroutineDispatchersProvider.getMainDispatcher()).thenReturn(Dispatchers.Unconfined)
        val viewState = Mockito.mock(`ImagesView$$State`::class.java)
        imagesPresenter.setViewState(viewState)
    }

    private fun captureSecondArg(): (String) -> Unit {
        doNothing().`when`(imagesPresenter.permissionManager).checkPermission(any(), argumentCaptor.capture(), any())
        imagesPresenter.onCameraButtonClicked()
        return argumentCaptor.firstValue
    }

    private fun captureThirdArg(): (String) -> Unit {
        doNothing().`when`(imagesPresenter.permissionManager).checkPermission(any(), any(), argumentCaptor.capture())
        imagesPresenter.onCameraButtonClicked()
        return argumentCaptor.firstValue
    }

    @Test
    fun `test onCameraButtonClicked when called callback onPermissionGranted and createImageFile returns valid url should call viewState's setImage`() {
        val testUrl = "testUrl"

        runBlocking {
            Mockito.`when`(imagesPresenter.createImageFile.execute(Unit)).thenReturn(testUrl)
            Mockito.`when`(imagesPresenter.takePhoto.execute(testUrl)).thenReturn(true)
        }

        captureSecondArg().invoke(PERMISSION_WRITE_EXTERNAL_STORAGE)
        verify(imagesPresenter.viewState).setImage(testUrl)
    }

    @Test
    fun `test onCameraButtonClicked when called callback onPermissionGranted and createImageFile throws ioException should call viewState's showTakePhotoFailureError`() {

        runBlocking {
            doThrow(IOException::class).`when`(imagesPresenter.createImageFile).execute(Unit)
        }
        captureSecondArg().invoke(PERMISSION_WRITE_EXTERNAL_STORAGE)

        verify(imagesPresenter.viewState).showTakePhotoFailureError()
    }

    @Test
    fun `test onCameraButtonClicked when called callback onPermissionGranted and createImageFile returns valid url but takePhoto's execute returns false should call viewState's showTakePhotoFailureError`() {
        val testUrl = "testUrl"

        runBlocking {
            Mockito.`when`(imagesPresenter.createImageFile.execute(Unit)).thenReturn(testUrl)
            Mockito.`when`(imagesPresenter.takePhoto.execute(testUrl)).thenReturn(false)
        }

        captureSecondArg().invoke(PERMISSION_WRITE_EXTERNAL_STORAGE)

        verify(imagesPresenter.viewState).showTakePhotoFailureError()
    }

    @Test
    fun `test onCameraButtonClicked when called callback onPermissionDenied should call viewState's showWriteExternaStoragePermissionDenied`() {
        captureThirdArg().invoke(PERMISSION_WRITE_EXTERNAL_STORAGE)
        verify(imagesPresenter.viewState).showWriteExternaStoragePermissionDenied()
    }

}
