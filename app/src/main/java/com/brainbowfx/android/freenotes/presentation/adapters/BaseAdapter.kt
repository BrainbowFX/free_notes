package com.brainbowfx.android.freenotes.presentation.adapters

import android.view.View
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.brainbowfx.android.freenotes.presentation.utils.DiffCallback

abstract class BaseAdapter<T, VH : BaseAdapter.ViewHolder<T>>(
    private val diffUtilCallback: DiffCallback<T>
) : RecyclerView.Adapter<VH>() {

    private var data: MutableList<T> = mutableListOf()

    fun setData(data: List<T>) {
        diffUtilCallback.setup(this.data, data)
        DiffUtil.calculateDiff(diffUtilCallback).dispatchUpdatesTo(this)
        this.data = data.toMutableList()
    }

    fun getItem(position: Int): T = data[position]

    fun getItems(): List<T> = data

    fun removeAt(position: Int) {
        data.removeAt(position)
        notifyItemRemoved(position)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.onBind(data[position])
    }

    override fun getItemCount(): Int = data.size

    fun interface AdapterListener {
        fun onItemClicked(position: Int)
    }

    abstract class ViewHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView) {
        abstract fun onBind(item: T)

        abstract fun unBind()
    }
}