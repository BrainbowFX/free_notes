package com.brainbowfx.android.freenotes.presentation.view.contract

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

interface SpeechView : MvpView {

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun showError(error: String)

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun showSpeechMessage()

    fun placeTextAtCursorPosition(text: String)

}