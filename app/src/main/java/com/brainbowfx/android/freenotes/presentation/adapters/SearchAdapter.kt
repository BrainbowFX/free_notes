package com.brainbowfx.android.freenotes.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.ColorInt
import com.brainbowfx.android.freenotes.R
import com.brainbowfx.android.freenotes.di.scopes.Activity
import com.brainbowfx.android.freenotes.domain.entities.Note
import com.brainbowfx.android.freenotes.presentation.utils.NotesDiffCallback
import com.brainbowfx.android.freenotes.presentation.utils.Selection
import javax.inject.Inject

@Activity
class SearchAdapter @Inject constructor(
    private val layoutInflater: LayoutInflater,
    private val selection: Selection,
    notesDiffCallback: NotesDiffCallback
) :
    BaseAdapter<Note, SearchAdapter.ViewHolder>(notesDiffCallback) {

    private var listener: AdapterListener? = null

    fun setSelection(selection: String, @ColorInt color: Int) {
        this.selection.pattern = selection
        this.selection.selectionColor = color
    }

    init {
        setHasStableIds(true)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            layoutInflater.inflate(R.layout.item_search_notes_list, parent, false),
            selection
        )

    fun setListener(listener: AdapterListener) {
        this.listener = listener
    }

    fun removeListener() {
        listener = null
    }

    override fun onViewRecycled(holder: ViewHolder) {
        holder.unBind()
        super.onViewRecycled(holder)
    }

    override fun getItemId(position: Int): Long = getItem(position).id

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val note = getItem(position)
        holder.onBind(note)
        holder.itemView.setOnClickListener { listener?.onItemClicked(holder.layoutPosition) }
    }

    class ViewHolder(itemView: View, private val selection: Selection) :
        BaseAdapter.ViewHolder<Note>(itemView) {
        private val tvTitle: TextView = itemView.findViewById(R.id.tvTitle)
        private val tvContent: TextView = itemView.findViewById(R.id.tvContent)

        override fun onBind(item: Note) {
            tvTitle.text = selection.buildSelection(item.title)
            tvContent.text = selection.buildSelection(item.text)
        }

        override fun unBind() {
            tvTitle.text = null
            tvContent.text = null
        }
    }
}