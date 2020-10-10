package com.brainbowfx.android.freenotes.presentation.abstraction

import android.view.View
import androidx.annotation.DrawableRes

interface FloatingActionButtonOwner {

    fun setupButton(
        @DrawableRes drawableRes: Int,
        fabAlignmentMode: Int,
        onClickListener: View.OnClickListener
    )
}