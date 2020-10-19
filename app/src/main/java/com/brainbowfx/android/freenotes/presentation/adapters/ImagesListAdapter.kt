package com.brainbowfx.android.freenotes.presentation.adapters

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.selection.ItemDetailsLookup
import androidx.recyclerview.selection.SelectionTracker
import com.brainbowfx.android.freenotes.R
import com.brainbowfx.android.freenotes.domain.entities.Image
import com.brainbowfx.android.freenotes.domain.mappers.Mapper
import com.brainbowfx.android.freenotes.presentation.utils.ImagesDiffCallback
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_images_list.view.*
import javax.inject.Inject

class ImagesListAdapter @Inject constructor(
    private val layoutInflater: LayoutInflater,
    private val urlToUriMapper: Mapper<String, Uri>,
    imagesDiffCallback: ImagesDiffCallback
) :
    BaseAdapter<Image, ImagesListAdapter.ViewHolder>(imagesDiffCallback) {

    private var listener: AdapterListener? = null

    private var tracker: SelectionTracker<Long>? = null

    init {
        setHasStableIds(true)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(layoutInflater.inflate(R.layout.item_images_list, parent, false), urlToUriMapper)

    fun setTracker(tracker: SelectionTracker<Long>) {
        this.tracker = tracker
    }

    fun setListener(listener: AdapterListener) {
        this.listener = listener
    }

    fun removeListener() {
        listener = null
    }

    fun getItemsById(ids: List<Long>): List<Image> =
        getItems().filterIndexed { index, _ -> index.toLong() in ids }

    override fun getItemId(position: Int): Long = position.toLong()


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val imagePath = getItem(position)
        tracker?.let { holder.bind(imagePath, it.isSelected(position.toLong())) }
        holder.itemView.setOnClickListener { listener?.onItemClicked(holder.layoutPosition) }
    }

    class ViewHolder(
        itemView: View,
        private val urlToUriMapper: Mapper<String, Uri>
    ) : BaseAdapter.ViewHolder<Image>(itemView) {

        fun getItemDetails(): ItemDetailsLookup.ItemDetails<Long> =
            object : ItemDetailsLookup.ItemDetails<Long>() {
                override fun getPosition(): Int = adapterPosition
                override fun getSelectionKey(): Long = getId()
            }

        fun getId(): Long = adapterPosition.toLong()

        fun bind(item: Image, isActivated: Boolean = false) {
            itemView.isActivated = isActivated
            itemView.ivImageCheckedIndicator.visibility =
                if (isActivated) View.VISIBLE else View.INVISIBLE
            onBind(item)
        }

        override fun onBind(item: Image) {
            val imageSize = itemView.context.resources.getDimension(R.dimen.notes_image_size).toInt()
            Glide.with(itemView.context)
                .load(urlToUriMapper.map(item.url))
                .placeholder(R.drawable.ic_image)
                .override(imageSize, imageSize)
                .into(itemView.arivNoteImage)
        }

        override fun unBind() {
            itemView.arivNoteImage.setImageDrawable(null)
        }
    }
}