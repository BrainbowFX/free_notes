package com.brainbowfx.android.simplenotes.domain.interactor

import com.brainbowfx.android.simplenotes.domain.abstraction.CameraController
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class TakePhoto @Inject constructor(private val cameraController: CameraController) : UseCase<String, Boolean>() {
    override suspend fun execute(params: String): Boolean = suspendCoroutine {
        cameraController.takePhoto(params) { onResult -> it.resume(onResult) }
    }
}
