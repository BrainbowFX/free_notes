package com.brainbowfx.android.freenotes.presentation.navigation

import androidx.navigation.NavController
import com.brainbowfx.android.freenotes.R
import com.brainbowfx.android.freenotes.di.scopes.Activity
import javax.inject.Inject

@Activity
class SearchRouterImpl @Inject constructor(private val navigationController: NavController?) : BaseRouter(navigationController) {
    override fun navigateNext() {
        navigationController?.navigate(R.id.searchFragment)
    }
}