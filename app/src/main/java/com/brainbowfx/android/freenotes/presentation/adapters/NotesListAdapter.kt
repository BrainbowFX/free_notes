package com.brainbowfx.android.freenotes.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.brainbowfx.android.freenotes.R
import com.brainbowfx.android.freenotes.di.scopes.Activity
import com.brainbowfx.android.freenotes.di.scopes.ActivityPerInstance
import com.brainbowfx.android.freenotes.domain.entities.Note
import com.brainbowfx.android.freenotes.presentation.utils.NotesDiffCalback
import com.bumptech.glide.Glide
import javax.inject.Inject

@ActivityPerInstance
class NotesListAdapter @Inject constructor(
    layoutInflater: LayoutInflater,
    notesDiffCalback: NotesDiffCalback
) : BaseNotesListAdapter(layoutInflater, notesDiffCalback) {

    private var listener: Listener? = null

    interface Listener : BaseListener {
        fun onItemDeleted(position: Int)
        fun onItemDuplicated(position: Int)
    }

    fun setListener(listener: Listener) {
        this.listener = listener
    }

    fun removeListener() {
        listener = null
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)

        holder.root.setOnClickListener { listener?.onItemClicked(holder.layoutPosition) }
        holder.ivMenu.setOnClickListener {
            val popup = PopupMenu(holder.root.context, it)
            popup.inflate(R.menu.notes_item_menu)
            popup.setOnMenuItemClickListener { menuItem ->
                when (menuItem.itemId) {
                    R.id.menuCopy -> {
                        listener?.onItemDuplicated(holder.layoutPosition)
                        true
                    }
                    R.id.menuDelete -> {
                        listener?.onItemDeleted(holder.layoutPosition)
                        true
                    }
                    else -> false
                }
            }
            popup.show()
        }
    }
}