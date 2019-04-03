package com.brainbowfx.android.simplenotes.presentation.navigation

import androidx.core.os.bundleOf
import androidx.navigation.NavController
import com.brainbowfx.android.simplenotes.R
import com.brainbowfx.android.simplenotes.di.scopes.ActivityPerInstance
import com.brainbowfx.android.simplenotes.domain.router.Router
import javax.inject.Inject

@ActivityPerInstance
class RouterImpl @Inject constructor(
    val navigationController: NavController?) : Router {

    private var onDestinationChangedListener: NavController.OnDestinationChangedListener? = null

    override fun navigateToNotesEdit(id: Long, duplicate: Boolean) {
        val bundle = bundleOf("id" to id, "duplicate" to duplicate)
        navigationController?.navigate(R.id.action_notesListFragment_to_notesEditFragment, bundle)
    }

    override fun navigateToNotesEdit() {
        navigationController?.navigate(R.id.action_notesListFragment_to_notesEditFragment)
    }

    override fun navigateToNotesEdit(id: Long) {
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