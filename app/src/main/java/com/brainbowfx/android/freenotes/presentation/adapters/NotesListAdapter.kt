package com.brainbowfx.android.freenotes.presentation.adapters

import android.view.LayoutInflater
import android.widget.PopupMenu
import com.brainbowfx.android.freenotes.R
import com.brainbowfx.android.freenotes.di.scopes.Activity
import com.brainbowfx.android.freenotes.di.scopes.Presenter
import com.brainbowfx.android.freenotes.presentation.utils.NotesDiffCallback
import javax.inject.Inject

@Activity
class NotesListAdapter @Inject constructor(
    layoutInflater: LayoutInflater,
    notesDiffCallback: NotesDiffCallback
) : BaseNotesListAdapter(layoutInflater, notesDiffCallback) {

    private var listener: Listener? = null

    interface Listener : AdapterListener {
        fun onItemDeleted(position: Int)
        fun onItemDuplicated(position: Int)
    }

    fun setListener(listener: Listener) {
        this.listener = listener
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)

        holder.itemView.setOnClickListener { listener?.onItemClicked(holder.layoutPosition) }
        holder.ivMenu.setOnClickListener {
            val popup = PopupMenu(holder.itemView.context, it)
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