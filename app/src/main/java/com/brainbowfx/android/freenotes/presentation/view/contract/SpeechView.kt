package com.brainbowfx.android.freenotes.presentation.view.contract

import com.arellomobile.mvp.MvpView

interface SpeechView : MvpView {

    fun showError(error: String)

    fun placeTextAtCursorPositon(text: String)

}