package com.brainbowfx.android.freenotes.presentation.utils

import com.brainbowfx.android.freenotes.di.scopes.Presenter
import com.brainbowfx.android.freenotes.domain.entities.Note
import javax.inject.Inject

@Presenter
class NotesDiffCallback @Inject constructor() : DiffCallback<Note>() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldData[oldItemPosition].id == newData[newItemPosition].id

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldData[oldItemPosition] == newData[newItemPosition]
}