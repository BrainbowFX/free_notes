package com.brainbowfx.android.freenotes.presentation.navigation

import androidx.core.os.bundleOf
import androidx.navigation.NavController
import com.brainbowfx.android.freenotes.R
import com.brainbowfx.android.freenotes.di.scopes.ActivityPerInstance
import com.brainbowfx.android.freenotes.domain.router.NotesEditRouter
import javax.inject.Inject

@ActivityPerInstance
class NotesEditRouterImpl @Inject constructor(private val navigationController: NavController?) : NotesEditRouter {

    private var onDestinationChangedListener: NavController.OnDestinationChangedListener? = null

    override fun navigateNext(id: Long, duplicate: Boolean) {
        val bundle = bundleOf("id" to id, "duplicate" to duplicate)
        navigationController?.navigate(R.id.action_notesListFragment_to_notesEditFragment, bundle)
    }

    override fun navigateNext() {
        navigationController?.navigate(R.id.action_notesListFragment_to_notesEditFragment)
    }

    override fun navigateNext(id: Long) {
        val bundle = bundleOf("id" to id)
        navigationController?.navigate(R.id.action_notesListFragment_to_notesEditFragment, bundle)
    }

    override fun addCallback(onDestinationChanged: (destinationId: Int) -> Unit) {
        onDestinationChangedListener = NavController.OnDestinationChangedListener { _, destination, _ ->
            onDestinationChanged(destination.id)
        }.also {
            navigationController?.addOnDestinationChangedListener(it)
        }
    }

    override fun removeCallback() {
        onDestinationChangedListener?.let { navigationController?.removeOnDestinationChangedListener(it) }

    }

    override fun returnBack(): Boolean = navigationController?.popBackStack() ?: false


}