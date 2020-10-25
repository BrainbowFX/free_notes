package com.brainbowfx.android.freenotes.presentation.presenter

import com.arellomobile.mvp.InjectViewState
import com.brainbowfx.android.freenotes.presentation.view.contract.SearchView

@InjectViewState
class SearchPresenter : ScopedPresenter<SearchView>() {

    fun onQueryChanged(query: String) {

    }
}