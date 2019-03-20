package com.brainbowfx.android.simplenotes.presentation.view.contract

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.brainbowfx.android.simplenotes.domain.entities.Note

interface NotesListView : MvpView {

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun setData(data: MutableList<Note>)

    @StateStrategyType(SkipStrategy::class)
    fun removeNoteAt(position: Int)
}