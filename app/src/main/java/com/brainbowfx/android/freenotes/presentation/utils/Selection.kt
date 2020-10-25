package com.brainbowfx.android.freenotes.presentation.utils

import android.graphics.Color
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import androidx.annotation.ColorInt
import java.lang.ref.WeakReference
import java.util.regex.Pattern

class Selection constructor(
    @ColorInt var selectionColor: Int = Color.RED
) {
    private val selectionChangedListeners = mutableListOf<WeakReference<OnSelectionChangedListener>>()

    var pattern: String = ""
        set(value) {
            field = value
            selectionChangedListeners.forEach { it.get()?.onSelectionChanged(value) }
        }

    fun addListener(listener: OnSelectionChangedListener) {
        selectionChangedListeners.add(WeakReference(listener))
    }

    fun removeListener(listener: OnSelectionChangedListener) {
        val iterator = selectionChangedListeners.iterator()
        while (iterator.hasNext()) {
            if (iterator.next().get() == listener) {
                iterator.remove()
            }
        }
    }

    fun buildSelection(inputString: String): SpannableString {
        val spannableString = SpannableString(inputString)
        Pattern.compile(pattern, Pattern.CASE_INSENSITIVE).run {
            val result = matcher(inputString)
            while (result.find()) {
                spannableString.setSpan(
                    ForegroundColorSpan(selectionColor),
                    result.start(),
                    result.end(),
                    SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE
                )
            }
        }
        return spannableString
    }

    fun interface OnSelectionChangedListener {
        fun onSelectionChanged(query: String)
    }
}