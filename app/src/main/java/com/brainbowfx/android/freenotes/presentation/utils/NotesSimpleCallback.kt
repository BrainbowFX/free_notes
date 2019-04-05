package com.brainbowfx.android.freenotes.presentation.utils

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

class NotesSimpleCallback(private val onItemDeleted: (Int) -> Unit) :
    ItemTouchHelper.SimpleCallback(ItemTouchHelper.ANIMATION_TYPE_DRAG, ItemTouchHelper.LEFT) {

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean = false

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        if (direction == ItemTouchHelper.LEFT) onItemDeleted(viewHolder.layoutPosition)
    }
}