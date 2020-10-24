package com.brainbowfx.android.freenotes.presentation.navigation

import androidx.navigation.NavController
import com.brainbowfx.android.freenotes.domain.router.Router

abstract class BaseRouter constructor(private val navigationController: NavController?) :
    Router {

    private var onDestinationChangedListener: NavController.OnDestinationChangedListener? = null

    abstract override fun navigateNext()

    override fun addCallback(onDestinationChanged: (destinationId: Int) -> Unit) {
        onDestinationChangedListener =
            NavController.OnDestinationChangedListener { _, destination, _ ->
                onDestinationChanged(destination.id)
            }.also {
                navigationController?.addOnDestinationChangedListener(it)
            }
    }

    override fun removeCallback() {
        onDestinationChangedListener?.let {
            navigationController?.removeOnDestinationChangedListener(
                it
            )
        }

    }

    override fun returnBack(): Boolean = navigationController?.popBackStack() ?: false


}