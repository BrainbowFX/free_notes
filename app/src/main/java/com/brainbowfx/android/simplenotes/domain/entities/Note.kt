package com.brainbowfx.android.simplenotes.domain.entities

class Note(var id: Long = -1,
           var title: String = "",
           var text: String = "",
           var dateTime: String = "",
           val imagePaths: MutableList<String> = mutableListOf())