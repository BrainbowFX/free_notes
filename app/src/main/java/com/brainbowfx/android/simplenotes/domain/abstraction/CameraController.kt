package com.brainbowfx.android.simplenotes.domain.abstraction

interface CameraController {

    var onCameraResult: ((success: Boolean) -> Unit)?

    fun takePhoto(url: String, onResult: ((success: Boolean) -> Unit)?)
}