package com.brainbowfx.android.freenotes.domain.router

interface Router {
    fun navigateToNotesEdit(id: Long)

    fun navigateToNotesEdit(id: Long, duplicate: Boolean)

    fun navigateToNotesEdit()

    fun returnBack(): Boolean

    fun addCallback(onDestinationChanged: (destinationId: Int) -> Unit)

    fun removeCallback()
}
