package com.brainbowfx.android.freenotes.domain.entities

data class Note(var id: Long = 0,
           var title: String = "",
           var text: String = "",
           var dateTime: String = "",
           var images: MutableList<Image> = mutableListOf()) {
}