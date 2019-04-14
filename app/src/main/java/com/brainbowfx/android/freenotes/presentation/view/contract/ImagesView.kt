package com.brainbowfx.android.freenotes.presentation.view.contract

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

interface ImagesView : MvpView {

    @StateStrategyType(SkipStrategy::class)
    fun setImage(image: String)

    @StateStrategyType(SkipStrategy::class)
    fun showTakePhotoFailureError()

    @StateStrategyType(SkipStrategy::class)
    fun showWriteExternaStoragePermissionDenied()
}