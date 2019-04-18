package com.brainbowfx.android.freenotes.presentation.presenter

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.brainbowfx.android.freenotes.domain.CoroutineDispatchersProvider
import com.brainbowfx.android.freenotes.domain.interactor.CreateImageFile
import com.brainbowfx.android.freenotes.domain.interactor.TakePhoto
import com.brainbowfx.android.freenotes.presentation.PERMISSION_WRITE_EXTERNAL_STORAGE
import com.brainbowfx.android.freenotes.presentation.utils.PermissionManager
import com.brainbowfx.android.freenotes.presentation.view.contract.ImagesView
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException
import javax.inject.Inject

@InjectViewState
class ImagesPresenter : MvpPresenter<ImagesView>() {

    @Inject
    lateinit var createImageFile: CreateImageFile

    @Inject
    lateinit var takePhoto: TakePhoto

    @Inject
    lateinit var permissionManager: PermissionManager

    @Inject
    lateinit var coroutineDispatchersProvider: CoroutineDispatchersProvider

    fun onCameraButtonClicked() {
        permissionManager.checkPermission(PERMISSION_WRITE_EXTERNAL_STORAGE,
            {
                GlobalScope.launch(coroutineDispatchersProvider.getMainDispatcher()) {
                    val url: String = createImageFile()
                    val isPhotoTaked: Boolean = takePhoto(url)
                    if (isPhotoTaked) viewState.setImage(url)
                    else viewState.showTakePhotoFailureError()
                }
            },
            { viewState.showWriteExternaStoragePermissionDenied() }
        )
    }

    private suspend fun takePhoto(url: String): Boolean = if (url.isNotEmpty()) takePhoto.execute(url) else false

    private suspend fun createImageFile(): String = try {
        withContext(coroutineDispatchersProvider.getIODispatcher()) { createImageFile.execute(Unit) }
    } catch (ioException: IOException) {
        ""
    }


}