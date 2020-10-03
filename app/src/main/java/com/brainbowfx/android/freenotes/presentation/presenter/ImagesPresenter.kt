package com.brainbowfx.android.freenotes.presentation.presenter

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.brainbowfx.android.freenotes.domain.CoroutineDispatchersProvider
import com.brainbowfx.android.freenotes.domain.interactor.CreateImageFile
import com.brainbowfx.android.freenotes.presentation.PERMISSION_WRITE_EXTERNAL_STORAGE
import com.brainbowfx.android.freenotes.presentation.utils.PermissionManager
import com.brainbowfx.android.freenotes.presentation.view.contract.ImagesView
import kotlinx.coroutines.*
import java.io.IOException
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

@InjectViewState
class ImagesPresenter : MvpPresenter<ImagesView>(), CoroutineScope {

    private val parentJob = SupervisorJob()

    @Inject
    lateinit var createImageFile: CreateImageFile

    @Inject
    lateinit var permissionManager: PermissionManager

    @Inject
    lateinit var coroutineDispatchersProvider: CoroutineDispatchersProvider

    override val coroutineContext: CoroutineContext
        get() = coroutineDispatchersProvider.getMainDispatcher() + parentJob

    fun onCameraButtonClicked() {
        permissionManager.checkPermission(PERMISSION_WRITE_EXTERNAL_STORAGE,
            {
                launch {
                    try {
                        createImageFile.execute(Unit)
                    } catch (ioException: IOException) {
                        viewState.showCreateTempFileFailureError()
                        null
                    }?.let(viewState::takePhoto)
                }
            },
            { viewState.showWriteExternalStoragePermissionDenied() }
        )
    }

    fun onPhotoTaken(url: String) {
        viewState.setImage(url)
    }

    override fun onDestroy() {
        super.onDestroy()
        parentJob.cancel()
    }


}