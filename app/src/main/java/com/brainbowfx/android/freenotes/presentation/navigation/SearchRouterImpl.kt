package com.brainbowfx.android.freenotes.presentation.navigation

import androidx.navigation.NavController
import com.brainbowfx.android.freenotes.R
import com.brainbowfx.android.freenotes.di.scopes.Activity
import javax.inject.Inject

@Activity
class SearchRouterImpl @Inject constructor(private val navigationController: NavController?) : BaseRouter(navigationController) {
    override fun navigateNext() {
        when(navigationController?.currentDestination?.id) {
            R.id.notesEditFragment -> {
                navigationController.navigate(R.id.action_notesEditFragment_to_searchFragment)
            }
            R.id.notesListFragment -> {
                navigationController.navigate(R.id.action_notesListFragment_to_searchFragment)
            }
        }

    }
}