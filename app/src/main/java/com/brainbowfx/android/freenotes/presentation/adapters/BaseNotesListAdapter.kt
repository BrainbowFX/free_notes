package com.brainbowfx.android.freenotes.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.brainbowfx.android.freenotes.R
import com.brainbowfx.android.freenotes.di.scopes.Activity
import com.brainbowfx.android.freenotes.domain.entities.Note
import com.brainbowfx.android.freenotes.presentation.utils.NotesDiffCallback
import com.bumptech.glide.Glide

@Activity
open class BaseNotesListAdapter constructor(
    private val layoutInflater: LayoutInflater,
    notesDiffCallback: NotesDiffCallback
) :
    BaseAdapter<Note, BaseNotesListAdapter.ViewHolder>(notesDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(layoutInflater.inflate(R.layout.item_notes_list, parent, false))

    class ViewHolder(itemView: View) : BaseAdapter.ViewHolder<Note>(itemView) {
        private val ivNotesTitleImage: ImageView = itemView.findViewById(R.id.ivNotesTitleImage)
        private val tvNotesHeader: TextView = itemView.findViewById(R.id.tvNotesHeader)
        private val tvNotesText: TextView = itemView.findViewById(R.id.tvNotesText)
        private val tvNotesDate: TextView = itemView.findViewById(R.id.tvNotesDate)
        val ivMenu: ImageView = itemView.findViewById(R.id.ibMenu)

        override fun onBind(item: Note) {
            tvNotesText.text = item.text
            tvNotesDate.text = item.dateTime

            if (item.title.isNotEmpty()) {
                tvNotesHeader.visibility = View.VISIBLE
                tvNotesHeader.text = item.title
            } else tvNotesHeader.visibility = View.GONE

            if (item.images.isNotEmpty()) {
                ivNotesTitleImage.visibility = View.VISIBLE
                Glide.with(itemView.context).load(item.images.first().url).into(ivNotesTitleImage)
            } else ivNotesTitleImage.visibility = View.GONE
        }

        override fun unBind() {
            tvNotesText.text = null
            tvNotesDate.text = null
            tvNotesHeader.text = null
            ivNotesTitleImage.setImageDrawable(null)
            ivMenu.setOnClickListener(null)
        }
    }
}