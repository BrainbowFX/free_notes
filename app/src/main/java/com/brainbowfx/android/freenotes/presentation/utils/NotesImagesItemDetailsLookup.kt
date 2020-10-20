package com.brainbowfx.android.freenotes.presentation.utils

import android.view.MotionEvent
import androidx.recyclerview.selection.ItemDetailsLookup
import androidx.recyclerview.widget.RecyclerView
import com.brainbowfx.android.freenotes.presentation.adapters.ImagesListAdapter

class NotesImagesItemDetailsLookup(
    private val recyclerView: RecyclerView,
    private val imagesListAdapter: ImagesListAdapter
) : ItemDetailsLookup<String>() {
    override fun getItemDetails(event: MotionEvent): ItemDetails<String>? {
        val view = recyclerView.findChildViewUnder(event.x, event.y)

        return view?.let {
            val position = recyclerView.getChildAdapterPosition(it)
            val item = imagesListAdapter.getItem(position)

            object : ItemDetailsLookup.ItemDetails<String>() {
                override fun getPosition(): Int = position
                override fun getSelectionKey(): String = item.imageId
            }
        }
    }
}