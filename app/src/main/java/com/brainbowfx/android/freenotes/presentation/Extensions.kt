package com.brainbowfx.android.freenotes.presentation

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import com.brainbowfx.android.freenotes.domain.entities.Note

fun Context.toDrawable(@DrawableRes drawableResId: Int): Drawable? =
    ContextCompat.getDrawable(this, drawableResId)

inline fun Note?.whenNotNullOrEmpty(block: (Note) -> Unit) {
    if (this != null && !this.isEmpty) {
        block(this)
    }
}