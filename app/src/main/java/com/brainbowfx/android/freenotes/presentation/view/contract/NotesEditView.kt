package com.brainbowfx.android.freenotes.presentation.view.contract

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

interface NotesEditView : MvpView {

    @StateStrategyType(SkipStrategy::class)
    fun setArgs(id: Long, duplicate: Boolean)

    fun setTitle(title: String)

    fun setInputText(inputText: String)

    fun setImages(imagesPaths: MutableList<String>)

    fun showDeleteButton()

    fun hideDeleteButton()

    fun removeImages(ids: List<Long>)

}