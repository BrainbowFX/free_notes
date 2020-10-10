package com.brainbowfx.android.freenotes.presentation.presenter

import com.arellomobile.mvp.InjectViewState
import com.brainbowfx.android.freenotes.domain.CoroutineDispatchersProvider
import com.brainbowfx.android.freenotes.domain.entities.Image
import com.brainbowfx.android.freenotes.domain.interactor.CreateImageFile
import com.brainbowfx.android.freenotes.domain.interactor.DeleteImages
import com.brainbowfx.android.freenotes.presentation.PERMISSION_WRITE_EXTERNAL_STORAGE
import com.brainbowfx.android.freenotes.presentation.abstraction.PermissionManager
import com.brainbowfx.android.freenotes.presentation.view.contract.ImagesView
import kotlinx.coroutines.*
import java.io.IOException
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

@InjectViewState
class ImagesPresenter : ScopedPresenter<ImagesView>() {

    @Inject
    lateinit var createImageFile: CreateImageFile

    @Inject
    lateinit var permissionManager: PermissionManager

    @Inject
    lateinit var deleteImages: DeleteImages

    @Inject
    override lateinit var coroutineDispatchersProvider: CoroutineDispatchersProvider

    override val coroutineContext: CoroutineContext by lazy {
        coroutineDispatchersProvider.getMainDispatcher() + SupervisorJob()
    }

    fun onCameraButtonClicked() {
        permissionManager.checkPermission(PERMISSION_WRITE_EXTERNAL_STORAGE,
            {
                launch {
                    try {
                        createImageFile.execute(Unit)
                    } catch (ioException: IOException) {
                        viewState.showCreateTempFileFailureError()
                        null
                    }?.let(viewState::checkCameraExistence)
                }
            },
            { viewState.showWriteExternalStoragePermissionDenied() }
        )
    }

    fun onDeleteImages(images: List<Image>) {
        launch {
            deleteImages.execute(images)
            viewState.deleteImages(images)
        }
    }

    fun onCameraExists(url: String) {
        viewState.takePhoto(url)
    }

    fun onCameraNotExists() {
        viewState.showNoCameraMessage()
    }

    fun onPhotoTaken(url: String) {
        viewState.setImage(Image(url = url))
    }

    override fun onDestroy() {
        super.onDestroy()
        coroutineContext[Job]?.cancel()
    }


}