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

    override fun navigateNext(id: Long, duplicate: Boolean) {
        navigationController?.navigate(
            R.id.action_notesListFragment_to_notesEditFragment,
            bundleOf("id" to id, "duplicate" to duplicate)
        )
    }

    override fun navigateNext() {
        navigationController?.navigate(R.id.action_notesListFragment_to_notesEditFragment)
    }

    override fun navigateNext(id: Long) {
        navigationController?.navigate(
            R.id.action_notesListFragment_to_notesEditFragment,
            bundleOf("id" to id)
        )
    }

}