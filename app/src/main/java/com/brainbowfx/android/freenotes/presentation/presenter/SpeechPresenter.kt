package com.brainbowfx.android.freenotes.presentation.presenter

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.brainbowfx.android.freenotes.data.speech.SpeechRecognitionService
import com.brainbowfx.android.freenotes.presentation.PERMISSION_RECORD_AUDIO
import com.brainbowfx.android.freenotes.presentation.abstraction.PermissionManager
import com.brainbowfx.android.freenotes.presentation.view.contract.SpeechView
import javax.inject.Inject

@InjectViewState
class SpeechPresenter : MvpPresenter<SpeechView>(), SpeechRecognitionService.SpeechRecognitionSubscriber {

    @Inject
    lateinit var permissionManager: PermissionManager

    @Inject
    lateinit var speechRecognitionService: SpeechRecognitionService

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        speechRecognitionService.subscribe(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        speechRecognitionService.unsubscribe()
    }

    fun onRecordVoiceButtonDown() {
        permissionManager.checkPermission(
            PERMISSION_RECORD_AUDIO,
            onPermissionGranted = {
                speechRecognitionService.start()
                viewState.showSpeechMessage()
            },
            onPermissionDenied = { Unit })
    }

    fun onRecordVoiceButtonUp() {
        speechRecognitionService.stop()
    }

    //SpeechRecognitionSubscriber implementation
    override fun onSpeechError(throwable: Throwable) {
        viewState.showError(throwable.message.toString())
    }

    override fun onSpeechResult(recognizedSpeech: String) {
        viewState.placeTextAtCursorPosition(recognizedSpeech)
    }

}