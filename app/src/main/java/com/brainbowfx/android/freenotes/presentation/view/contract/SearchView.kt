package com.brainbowfx.android.freenotes.presentation.view.contract

import com.arellomobile.mvp.MvpView
import com.brainbowfx.android.freenotes.domain.entities.Note

interface SearchView : MvpView {
    fun setResult(notes: List<Note>)
}