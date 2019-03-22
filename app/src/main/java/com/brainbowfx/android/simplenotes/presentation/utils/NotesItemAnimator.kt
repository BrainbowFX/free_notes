package com.brainbowfx.android.simplenotes.presentation.utils

import android.animation.Animator
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.RecyclerView

class NotesItemAnimator : DefaultItemAnimator() {

    override fun animateRemove(holder: RecyclerView.ViewHolder?): Boolean {
        if (holder == null) return false
        holder.itemView.animate()
            .setInterpolator(AccelerateDecelerateInterpolator())
            .setDuration(120)
            .translationXBy(holder.itemView.left.toFloat())
            .translationX(-holder.itemView.right.toFloat())
            .setListener(object : Animator.AnimatorListener {
                override fun onAnimationRepeat(animation: Animator?) = Unit

                override fun onAnimationCancel(animation: Animator?) { animation?.setupEndValues() }

                override fun onAnimationEnd(animation: Animator?) = dispatchRemoveFinished(holder)

                override fun onAnimationStart(animation: Animator?) = dispatchRemoveStarting(holder)
            }).start()

        return true
    }

}