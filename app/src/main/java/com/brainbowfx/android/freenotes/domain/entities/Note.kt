package com.brainbowfx.android.freenotes.domain.entities

data class Note(var id: Long = -1,
           var title: String = "",
           var text: String = "",
           var dateTime: String = "",
           var imagePaths: MutableList<String> = mutableListOf()) {
}