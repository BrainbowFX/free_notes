package com.brainbowfx.android.freenotes.domain.router

interface NotesRouter : Router {

    fun navigateNext(id: Long, duplicate: Boolean = false)

}

