package com.brainbowfx.android.freenotes.domain.entities

class Note(var id: Long = -1,
           var title: String = "",
           var text: String = "",
           var dateTime: String = "",
           var imagePaths: MutableList<String> = mutableListOf()) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Note

        if (id != other.id) return false
        if (title != other.title) return false
        if (text != other.text) return false
        if (dateTime != other.dateTime) return false
        if (imagePaths != other.imagePaths) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + title.hashCode()
        result = 31 * result + text.hashCode()
        result = 31 * result + dateTime.hashCode()
        result = 31 * result + imagePaths.hashCode()
        return result
    }
}