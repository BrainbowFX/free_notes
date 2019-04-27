package com.brainbowfx.android.freenotes.domain.router

interface NotesEditRouter : Router {

    fun navigateNext(id: Long)

    fun navigateNext(id: Long, duplicate: Boolean)

}

