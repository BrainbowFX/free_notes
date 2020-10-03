package com.brainbowfx.android.freenotes.presentation.view.contract

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

interface ImagesView : MvpView {

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun setImage(image: String)

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun showTakePhotoFailureError()

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun showCreateTempFileFailureError()

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun showWriteExternalStoragePermissionDenied()

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun takePhoto(url: String)
}