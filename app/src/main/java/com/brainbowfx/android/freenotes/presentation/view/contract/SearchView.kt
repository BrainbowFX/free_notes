package com.brainbowfx.android.freenotes.presentation.view.contract

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.brainbowfx.android.freenotes.domain.entities.Note

interface SearchView : MvpView {
    fun setResult(query: String, notes: List<Note>)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun setupButton()
}