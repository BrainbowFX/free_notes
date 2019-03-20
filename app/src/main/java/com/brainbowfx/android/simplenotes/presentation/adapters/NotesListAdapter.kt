package com.brainbowfx.android.simplenotes.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.brainbowfx.android.simplenotes.R
import com.brainbowfx.android.simplenotes.di.scopes.Activity
import com.brainbowfx.android.simplenotes.domain.entities.Note
import javax.inject.Inject

@Activity
class NotesListAdapter @Inject constructor(private val layoutInflater: LayoutInflater) :
    RecyclerView.Adapter<NotesListAdapter.ViewHolder>() {

    private var listener: Listener? = null
    private var notes: MutableList<Note> = mutableListOf()

    interface Listener {
        fun onItemClicked(position: Int)
        fun onItemDeleted(position: Int)
        fun onItemDuplicated(position: Int)
    }

    fun setListener(listener: Listener) {
        this.listener = listener
    }

    fun removeListener() {
        listener = null
    }

    fun getItem(position: Int): Note {
        return notes[position]
    }


    fun setData(notes: MutableList<Note>) {
        this.notes = notes
        notifyDataSetChanged()
    }

    fun removeAt(position: Int) {
        notes.removeAt(position)
        notifyItemRemoved(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesListAdapter.ViewHolder =
        ViewHolder(layoutInflater.inflate(R.layout.item_notes_list, parent, false))

    override fun getItemCount(): Int = notes.size

    override fun onBindViewHolder(holder: NotesListAdapter.ViewHolder, position: Int) {
        val note = notes[position]
        holder.tvNotesHeader.text = note.title
        holder.tvNotesText.text = note.text
        holder.tvNotesDate.text = note.dateTime
        holder.root.setOnClickListener { listener?.onItemClicked(position) }
        holder.ivMenu.setOnClickListener {
            val popup = PopupMenu(holder.root.context, it)
            popup.inflate(R.menu.notes_item_menu)
            popup.setOnMenuItemClickListener { menuItem ->
                when (menuItem.itemId) {
                    R.id.menuCopy -> {
                        listener?.onItemDuplicated(position)
                        true
                    }
                    R.id.menuDelete -> {
                        listener?.onItemDeleted(position)
                        true
                    }
                    else -> false
                }
            }
            popup.show()
        }
    }

    class ViewHolder(val root: View) : RecyclerView.ViewHolder(root) {
        val ivNotesTitleImage: ImageView = root.findViewById(R.id.ivNotesTitleImage)
        val tvNotesHeader: TextView = root.findViewById(R.id.tvNotesHeader)
        val tvNotesText: TextView = root.findViewById(R.id.tvNotesText)
        val tvNotesDate: TextView = root.findViewById(R.id.tvNotesDate)
        val ivMenu: ImageView = root.findViewById(R.id.ibMenu)
    }
}