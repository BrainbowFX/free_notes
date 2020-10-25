package com.brainbowfx.android.freenotes.presentation.navigation

import androidx.core.os.bundleOf
import androidx.navigation.NavController
import com.brainbowfx.android.freenotes.R
import com.brainbowfx.android.freenotes.di.scopes.Activity
import com.brainbowfx.android.freenotes.domain.router.NotesRouter
import javax.inject.Inject

@Activity
class NotesRouterImpl @Inject constructor(private val navigationController: NavController?) :
    BaseRouter(navigationController), NotesRouter {

    private fun getAction(): Int? = when (navigationController?.currentDestination?.id) {
        R.id.searchFragment -> R.id.action_searchFragment_to_notesEditFragment
        R.id.notesListFragment -> R.id.action_notesListFragment_to_notesEditFragment
        else -> null
    }

    override fun navigateNext(id: Long, duplicate: Boolean) {
        val bundle = bundleOf("id" to id, "duplicate" to duplicate)
        val action = getAction() ?: return
        navigationController?.navigate(
            action,
            bundle
        )
    }

    override fun navigateNext() {
        val action = getAction() ?: return
        navigationController?.navigate(action)
    }

}