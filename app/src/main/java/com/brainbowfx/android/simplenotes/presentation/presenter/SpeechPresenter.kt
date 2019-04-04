package com.brainbowfx.android.simplenotes.presentation.presenter

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.brainbowfx.android.simplenotes.data.speech.SpeechRecognitionService
import com.brainbowfx.android.simplenotes.presentation.PERMISSION_RECORD_AUDIO
import com.brainbowfx.android.simplenotes.presentation.utils.PermissionManager
import com.brainbowfx.android.simplenotes.presentation.view.contract.SpeechView
import javax.inject.Inject

@InjectViewState
class SpeechPresenter : MvpPresenter<SpeechView>(), SpeechRecognitionService.SpeechRecognitionSubscriber {

    private lateinit var speechView: SpeechView

    @Inject
    lateinit var permissionManager: PermissionManager

    @Inject
    lateinit var speechRecognitionService: SpeechRecognitionService

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        speechRecognitionService.subscribe(this)
        speechView = viewState
    }

    override fun onDestroy() {
        super.onDestroy()
        speechRecognitionService.unsubscribe()
    }

    fun onRecordVoiceButtonDown() {
        permissionManager.checkPermission(
            PERMISSION_RECORD_AUDIO,
            onPermissionGranted = { speechRecognitionService.start() },
            onPermissionDenied = { Unit })
    }

    fun onRecordVoiceButtonUp() {
        speechRecognitionService.stop()
    }

    //SpeechRecognitionSubscriber implementation
    override fun onSpeechError(throwable: Throwable) {
        speechView.showError(throwable.message.toString())
    }

    override fun onSpeechResult(recognizedSpeech: String) {
        speechView.placeTextAtCursorPositon(recognizedSpeech)
    }

}