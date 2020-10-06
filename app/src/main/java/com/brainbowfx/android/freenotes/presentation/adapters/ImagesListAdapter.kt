package com.brainbowfx.android.freenotes.presentation.adapters

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.selection.ItemDetailsLookup
import androidx.recyclerview.selection.SelectionTracker
import androidx.recyclerview.widget.RecyclerView
import com.brainbowfx.android.freenotes.R
import com.brainbowfx.android.freenotes.domain.entities.Image
import com.brainbowfx.android.freenotes.domain.mappers.Mapper
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_images_list.view.*
import javax.inject.Inject

class ImagesListAdapter @Inject constructor(
    private val layoutInflater: LayoutInflater,
    private val urlToUriMapper: Mapper<String, Uri>
) :
    RecyclerView.Adapter<ImagesListAdapter.ViewHolder>() {

    private var data: MutableList<Image> = mutableListOf()
    private var listener: ((position: Int) -> Unit)? = null
    private var tracker: SelectionTracker<Long>? = null

    init {
        setHasStableIds(true)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(layoutInflater.inflate(R.layout.item_images_list, parent, false), urlToUriMapper)

    override fun getItemCount(): Int = data.size

    fun getItem(position: Int) = data[position]

    fun setTracker(tracker: SelectionTracker<Long>) {
        this.tracker = tracker
    }

    fun setListener(listener: ((position: Int) -> Unit)?) {
        this.listener = listener
    }

    fun removeListener() {
        listener = null
    }

    override fun getItemId(position: Int): Long = position.toLong()

    fun remove(ids: List<Long>) {
        val values = ids.map { data[it.toInt()] }
        data.removeAll(values)
        notifyDataSetChanged()
    }

    fun addItem(imagePath: Image) {
        data.add(imagePath)
        notifyItemInserted(data.size - 1)
    }

    fun setData(data: MutableList<Image>) {
        this.data = data
        notifyDataSetChanged()
    }

    fun getItems(): MutableList<Image> = data

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val imagePath = data[position]
        tracker?.let { holder.bind(imagePath, it.isSelected(position.toLong())) }
        holder.root.setOnClickListener { listener?.invoke(holder.layoutPosition) }
    }

    class ViewHolder(val root: View, private val urlToUriMapper: Mapper<String, Uri>) : RecyclerView.ViewHolder(root) {
        fun getItemDetails(): ItemDetailsLookup.ItemDetails<Long> =
            object : ItemDetailsLookup.ItemDetails<Long>() {
                override fun getPosition(): Int = adapterPosition
                override fun getSelectionKey(): Long = getId()
            }

        fun getId(): Long = adapterPosition.toLong()

        fun bind(data: Image, isActivated: Boolean = false) {
            Glide.with(root.context).load(urlToUriMapper.map(data.url)).into(root.arivNoteImage)
            itemView.isActivated = isActivated
            root.ivImageCheckedIndicator.visibility = if (isActivated) View.VISIBLE else View.INVISIBLE
        }
    }
}