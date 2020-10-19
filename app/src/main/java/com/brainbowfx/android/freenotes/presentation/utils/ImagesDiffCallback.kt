package com.brainbowfx.android.freenotes.presentation.utils

import com.brainbowfx.android.freenotes.di.scopes.Presenter
import com.brainbowfx.android.freenotes.domain.entities.Image
import javax.inject.Inject

@Presenter
class ImagesDiffCallback @Inject constructor() : DiffCallback<Image>() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldData[oldItemPosition].imageId == newData[newItemPosition].imageId

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldData[oldItemPosition].url == newData[newItemPosition].url
}