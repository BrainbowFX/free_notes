package com.brainbowfx.android.freenotes.presentation.utils

import androidx.recyclerview.widget.DiffUtil

abstract class DiffCallback<T> : DiffUtil.Callback() {
    protected lateinit var oldData: List<T>
    protected lateinit var newData: List<T>

    fun setup(oldData: List<T>, newData: List<T>) {
        this.oldData = oldData
        this.newData = newData
    }

    override fun getOldListSize(): Int = oldData.size

    override fun getNewListSize(): Int = newData.size

    abstract override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean

    abstract override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean
}