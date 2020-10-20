package com.brainbowfx.android.freenotes.presentation.utils

import androidx.recyclerview.selection.ItemKeyProvider
import com.brainbowfx.android.freenotes.presentation.adapters.ImagesListAdapter

class ImagesItemKeyProvider(private val adapter: ImagesListAdapter): ItemKeyProvider<String>(SCOPE_CACHED) {
    override fun getKey(position: Int): String = adapter.getItem(position).imageId
    override fun getPosition(key: String): Int = adapter.getPosition(key)
}