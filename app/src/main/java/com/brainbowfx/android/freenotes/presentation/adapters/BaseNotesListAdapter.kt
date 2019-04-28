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
import com.brainbowfx.android.freenotes.domain.entities.Note
import com.brainbowfx.android.freenotes.presentation.utils.NotesDiffCalback
import com.bumptech.glide.Glide
import javax.inject.Inject

@Activity
open class BaseNotesListAdapter constructor(
    private val layoutInflater: LayoutInflater,
    private val notesDiffCalback: NotesDiffCalback
) :
    RecyclerView.Adapter<BaseNotesListAdapter.ViewHolder>() {

    private var notes: MutableList<Note> = mutableListOf()

    interface BaseListener {
        fun onItemClicked(position: Int)
    }

    fun getItem(position: Int): Note {
        return notes[position]
    }

    fun setData(notes: MutableList<Note>) {
        notesDiffCalback.setup(this.notes, notes)
        DiffUtil.calculateDiff(notesDiffCalback).dispatchUpdatesTo(this)
        this.notes = notes
    }

    fun removeAt(position: Int) {
        notes.removeAt(position)
        notifyItemRemoved(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(layoutInflater.inflate(R.layout.item_notes_list, parent, false))

    override fun getItemCount(): Int = notes.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val note = notes[position]
        holder.tvNotesText.text = note.text
        holder.tvNotesDate.text = note.dateTime

        if (note.title.isNotEmpty()) {
            holder.tvNotesHeader.visibility = View.VISIBLE
            holder.tvNotesHeader.text = note.title
        } else holder.tvNotesHeader.visibility = View.GONE

        if (note.imagePaths.isNotEmpty()) {
            holder.ivNotesTitleImage.visibility = View.VISIBLE
            Glide.with(holder.root.context).load(note.imagePaths[0]).into(holder.ivNotesTitleImage)
        } else holder.ivNotesTitleImage.visibility = View.GONE
    }

    class ViewHolder(val root: View) : RecyclerView.ViewHolder(root) {
        val ivNotesTitleImage: ImageView = root.findViewById(R.id.ivNotesTitleImage)
        val tvNotesHeader: TextView = root.findViewById(R.id.tvNotesHeader)
        val tvNotesText: TextView = root.findViewById(R.id.tvNotesText)
        val tvNotesDate: TextView = root.findViewById(R.id.tvNotesDate)
        val ivMenu: ImageView = root.findViewById(R.id.ibMenu)
    }
}