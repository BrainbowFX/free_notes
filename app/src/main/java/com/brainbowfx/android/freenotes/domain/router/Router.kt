package com.brainbowfx.android.freenotes.domain.router

interface Router {

    fun navigateNext()

    fun returnBack(): Boolean

    fun addCallback(onDestinationChanged: (destinationId: Int) -> Unit)

    fun removeCallback()

}