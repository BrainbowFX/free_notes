package com.brainbowfx.android.freenotes.presentation.adapters

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.selection.ItemDetailsLookup
import androidx.recyclerview.selection.SelectionTracker
import androidx.recyclerview.widget.RecyclerView
import com.brainbowfx.android.freenotes.R
import com.brainbowfx.android.freenotes.domain.mappers.Mapper
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_images_list.view.*
import javax.inject.Inject

class ImagesListAdapter @Inject constructor(
    private val layoutInflater: LayoutInflater,
    private val urlToUriMapper: Mapper<String, Uri>
) :
    RecyclerView.Adapter<ImagesListAdapter.ViewHolder>() {

    private var imagesPaths: MutableList<String> = mutableListOf()
    private var listener: ((position: Int) -> Unit)? = null
    private var tracker: SelectionTracker<Long>? = null

    init {
        setHasStableIds(true)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(layoutInflater.inflate(R.layout.item_images_list, parent, false), urlToUriMapper)

    override fun getItemCount(): Int = imagesPaths.size

    fun getItem(position: Int) = imagesPaths[position]

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
        val values = ids.map { imagesPaths[it.toInt()] }
        imagesPaths.removeAll(values)
        notifyDataSetChanged()
    }

    fun addItem(imagePath: String) {
        imagesPaths.add(imagePath)
        notifyItemInserted(imagesPaths.size - 1)
    }

    fun setData(imagesPaths: MutableList<String>) {
        this.imagesPaths = imagesPaths
        notifyDataSetChanged()
    }

    fun getItems(): MutableList<String> = imagesPaths

    override fun onBindViewHolder(holder: ImagesListAdapter.ViewHolder, position: Int) {
        val imagePath = imagesPaths[position]
        tracker?.let { holder.bind(imagePath, it.isSelected(position.toLong())) }
        holder.root.setOnClickListener { listener?.invoke(holder.layoutPosition) }
    }

    class ViewHolder(val root: View, val urlToUriMapper: Mapper<String, Uri>) : RecyclerView.ViewHolder(root) {
        fun getItemDetails(): ItemDetailsLookup.ItemDetails<Long> =
            object : ItemDetailsLookup.ItemDetails<Long>() {
                override fun getPosition(): Int = adapterPosition
                override fun getSelectionKey(): Long = getId()
            }

        fun getId(): Long = adapterPosition.toLong()

        fun bind(imagePath: String, isActivated: Boolean = false) {
            Glide.with(root.context).load(urlToUriMapper.map(imagePath)).into(root.arivNoteImage)
            itemView.isActivated = isActivated
            root.ivImageCheckedIndicator.visibility = if (isActivated) View.VISIBLE else View.INVISIBLE
        }
    }
}