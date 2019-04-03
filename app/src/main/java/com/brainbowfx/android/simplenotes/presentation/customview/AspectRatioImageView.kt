package com.brainbowfx.android.simplenotes.presentation.customview

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView
import com.brainbowfx.android.simplenotes.R

class AspectRatioImageView : ImageView {

    companion object {
        const val DEFAULT_ASPECT_RATIO = 1F
    }

    var aspectRatio: Float =
        DEFAULT_ASPECT_RATIO

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init(context, attrs)
    }

    private fun init(context: Context, attrs: AttributeSet?) {
        attrs?.let {
            val styledAttributes = context.obtainStyledAttributes(it, R.styleable.AspectRatioImageView)
            aspectRatio = styledAttributes.getFloat(R.styleable.AspectRatioImageView_aspectRatio,
                DEFAULT_ASPECT_RATIO
            )
            styledAttributes.recycle()
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        var width = measuredWidth
        var height = measuredHeight

        if (width == 0 && height == 0) return
        if (width > 0) height = (width * aspectRatio).toInt() else width = (height / aspectRatio).toInt()

        setMeasuredDimension(width, height)
    }
}