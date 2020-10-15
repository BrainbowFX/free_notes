package com.brainbowfx.android.freenotes

import com.brainbowfx.android.freenotes.di.DaggerTestComponentHolder
import com.brainbowfx.android.freenotes.presentation.PERMISSION_WRITE_EXTERNAL_STORAGE
import com.brainbowfx.android.freenotes.presentation.presenter.ImagesPresenter
import com.brainbowfx.android.freenotes.presentation.view.contract.`ImagesView$$State`
import com.nhaarman.mockitokotlin2.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.runner.RunWith

import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import java.io.IOException

@RunWith(MockitoJUnitRunner::class)
class ImagesPresenterTest {

    private val imagesPresenter: ImagesPresenter = ImagesPresenter()
    private val argumentCaptor = argumentCaptor<((String)-> Unit)>()


    init {
        DaggerTestComponentHolder.testComponent.inject(imagesPresenter)
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
    fun `test onCameraButtonClicked when permission denied, should call show showWriteExternalStoragePermissionDenied`() {

        captureThirdArg().invoke(PERMISSION_WRITE_EXTERNAL_STORAGE)

        verify(imagesPresenter.viewState).showWriteExternalStoragePermissionDenied()
    }

    @Test
    fun `test onCameraButtonClicked when called callback onPermissionGranted and createImageFile throws ioException should call viewState's showCreateTempFileFailureError`() {

        runBlocking {
            doThrow(IOException::class).`when`(imagesPresenter.createImageFile).execute(Unit)
        }
        captureSecondArg().invoke(PERMISSION_WRITE_EXTERNAL_STORAGE)

        verify(imagesPresenter.viewState).showCreateTempFileFailureError()
    }

    @Test
    fun `test onCameraButtonClicked when called callback onPermissionGranted and createImageFile returns valid url should call ImagesView checkCameraExistence method`() {
        val testUrl = "testUrl"

        runBlocking {
            Mockito.`when`(imagesPresenter.createImageFile.execute(Unit)).thenReturn(testUrl)
        }

        captureSecondArg().invoke(PERMISSION_WRITE_EXTERNAL_STORAGE)

        verify(imagesPresenter.viewState).checkCameraExistence(testUrl)
    }

    @Test
    fun `test onCameraButtonClicked when called callback onPermissionDenied should call viewState's showWriteExternaStoragePermissionDenied`() {
        captureThirdArg().invoke(PERMISSION_WRITE_EXTERNAL_STORAGE)
        verify(imagesPresenter.viewState).showWriteExternalStoragePermissionDenied()
    }

}
