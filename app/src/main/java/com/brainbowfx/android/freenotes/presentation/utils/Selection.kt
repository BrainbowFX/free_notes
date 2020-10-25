package com.brainbowfx.android.freenotes.presentation.utils

import android.graphics.Color
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import androidx.annotation.ColorInt
import java.util.regex.Pattern

class Selection constructor(
    @ColorInt var selectionColor: Int = Color.RED
) {
    var pattern: String = ""

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
}