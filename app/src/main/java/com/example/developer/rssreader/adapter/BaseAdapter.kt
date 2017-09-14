package com.example.developer.rssreader.adapter

import android.support.annotation.LayoutRes
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.developer.rssreader.extension.weak
import java.lang.ref.WeakReference


abstract class BaseAdapter<VH : BaseAdapter.ViewHolder, I>(
        listener: ItemClickListener? = null,
        items: List<I>? = null
) : RecyclerView.Adapter<VH>() {

    private val listener: WeakReference<ItemClickListener>? = listener?.weak()
    val items: MutableList<I> = items?.toMutableList() ?: mutableListOf()

    override fun getItemCount() = items.count()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return onCreateViewHolder(parent, viewType, listener)
    }

    abstract fun onCreateViewHolder(parent: ViewGroup, viewType: Int, listener: WeakReference<ItemClickListener>?): VH

    override fun onBindViewHolder(holder: VH, position: Int) {
        onBindViewHolder(holder, position, items[position])
    }

    abstract fun onBindViewHolder(holder: VH, position: Int, item: I)

    fun updateItems(items: List<I>?) {
        this.items.clear()

        if (items != null && items.isNotEmpty()) {
            this.items.addAll(items)
        }

        notifyDataSetChanged()
    }

    interface ItemClickListener {
        fun onItemClick(position: Int)
    }

    open class ViewHolder(container: ViewGroup, @LayoutRes layoutResId: Int, private val listener: WeakReference<ItemClickListener>?) : RecyclerView.ViewHolder(
            LayoutInflater.from(container.context).inflate(layoutResId, container, false)
    ) {
        init {
            itemView.setOnClickListener { listener?.get()?.onItemClick(adapterPosition) }

        }
    }
}