package com.brainbowfx.android.freenotes.presentation.utils

import android.view.MotionEvent
import androidx.recyclerview.selection.ItemDetailsLookup
import androidx.recyclerview.widget.RecyclerView
import com.brainbowfx.android.freenotes.presentation.adapters.ImagesListAdapter

class NotesImagesItemDetailsLookup(private val recyclerView: RecyclerView) : ItemDetailsLookup<Long>() {
    override fun getItemDetails(event: MotionEvent): ItemDetails<Long>? {
        val view = recyclerView.findChildViewUnder(event.x, event.y)

        return if (view != null)
            return (recyclerView.getChildViewHolder(view) as ImagesListAdapter.ViewHolder).getItemDetails()
        else null
    }
}