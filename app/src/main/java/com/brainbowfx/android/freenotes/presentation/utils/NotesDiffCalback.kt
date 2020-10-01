package com.brainbowfx.android.freenotes.presentation.utils

import androidx.recyclerview.widget.DiffUtil
import com.brainbowfx.android.freenotes.di.scopes.Activity
import com.brainbowfx.android.freenotes.di.scopes.ActivityPerInstance
import com.brainbowfx.android.freenotes.domain.entities.Note
import javax.inject.Inject

@ActivityPerInstance
class NotesDiffCalback @Inject constructor() : DiffUtil.Callback() {

    private var oldData: List<Note> = listOf()
    private var newData: List<Note> = listOf()

    fun setup(oldData: List<Note>, newData: List<Note>) {
        this.oldData = oldData
        this.newData = newData
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldData[oldItemPosition].id == newData[newItemPosition].id

    override fun getOldListSize(): Int = oldData.size

    override fun getNewListSize(): Int = newData.size

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldData[oldItemPosition] == newData[newItemPosition]
}