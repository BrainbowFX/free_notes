package com.brainbowfx.android.simplenotes.presentation.presenter

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.brainbowfx.android.simplenotes.domain.CoroutineDispatchersProvider
import com.brainbowfx.android.simplenotes.domain.interactor.CreateImageFile
import com.brainbowfx.android.simplenotes.domain.interactor.TakePhoto
import com.brainbowfx.android.simplenotes.presentation.PERMISSION_WRITE_EXTERNAL_STORAGE
import com.brainbowfx.android.simplenotes.presentation.utils.PermissionManager
import com.brainbowfx.android.simplenotes.presentation.view.contract.ImagesView
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
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
                    val url = withContext(coroutineDispatchersProvider.getIODispatcher()) { createImageFile.execute(Unit)}
                    val success = if (url.isNotEmpty()) takePhoto.execute(url) else false
                    if (success) viewState.setImage(url)
                }
            },
            {

            })

    }


}