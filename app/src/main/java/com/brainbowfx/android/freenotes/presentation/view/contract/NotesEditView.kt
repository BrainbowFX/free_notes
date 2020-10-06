package com.brainbowfx.android.freenotes.presentation.view.contract

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.brainbowfx.android.freenotes.domain.entities.Image

interface NotesEditView : MvpView {

    @StateStrategyType(SkipStrategy::class)
    fun setArgs(id: Long, duplicate: Boolean)

    @StateStrategyType(SkipStrategy::class)
    fun setTitle(title: String)

    @StateStrategyType(SkipStrategy::class)
    fun setInputText(inputText: String)

    fun setImages(imagesPaths: MutableList<Image>)

    fun showDeleteButton()

    fun hideDeleteButton()

    @StateStrategyType(SkipStrategy::class)
    fun removeImages(ids: List<Long>)

}