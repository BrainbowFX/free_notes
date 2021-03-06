package com.brainbowfx.android.freenotes.data.speech

import android.content.Intent
import android.os.Bundle
import android.speech.RecognitionListener
import android.speech.SpeechRecognizer
import com.brainbowfx.android.freenotes.RECOGNIZER_INTENT_ID
import com.brainbowfx.android.freenotes.di.scopes.Presenter
import javax.inject.Inject
import javax.inject.Named

@Presenter
class SpeechRecognitionService @Inject constructor(
    @Named(RECOGNIZER_INTENT_ID) private val recognitionIntent: Intent,
    private val speechRecognizer: SpeechRecognizer
) :
    RecognitionListener {

    private var subscriber: SpeechRecognitionSubscriber? = null

    override fun onReadyForSpeech(p0: Bundle?) {}

    override fun onRmsChanged(p0: Float) {}

    override fun onBufferReceived(p0: ByteArray?) {}

    override fun onPartialResults(result: Bundle?) {
        val speechArray = result?.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)
        subscriber?.onSpeechResult(speechArray?.get(0) ?: "")
    }

    override fun onEvent(p0: Int, p1: Bundle?) {}

    override fun onBeginningOfSpeech() {}

    override fun onEndOfSpeech() {}

    override fun onError(errorCode: Int) {
        val throwable = when (errorCode) {
            SpeechRecognizer.ERROR_NETWORK, SpeechRecognizer.ERROR_NETWORK_TIMEOUT -> Throwable("network_error")
            SpeechRecognizer.ERROR_INSUFFICIENT_PERMISSIONS -> Throwable("insufficient_permissions")
            else -> Throwable("speech_recognition_service_failure")
        }
        subscriber?.onSpeechError(throwable)
    }

    fun start() {
        speechRecognizer.startListening(recognitionIntent)
    }

    fun cancel() {
        speechRecognizer.cancel()
    }

    fun stop() {
        speechRecognizer.stopListening()
    }

    override fun onResults(result: Bundle?) {
        val speechArray = result?.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)
        subscriber?.onSpeechResult(speechArray?.get(0) ?: "")
    }

    interface SpeechRecognitionSubscriber {
        fun onSpeechResult(recognizedSpeech: String)
        fun onSpeechError(throwable: Throwable)
    }

    fun subscribe(subscriber: SpeechRecognitionSubscriber) {
        speechRecognizer.setRecognitionListener(this)
        this.subscriber = subscriber
    }

    fun unsubscribe() {
        speechRecognizer.setRecognitionListener(null)
        subscriber = null
    }
}