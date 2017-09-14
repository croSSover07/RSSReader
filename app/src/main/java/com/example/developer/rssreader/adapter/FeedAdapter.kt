package com.example.developer.rssreader.adapter

import android.view.ViewGroup
import com.example.developer.rssreader.model.FeedEntry
import com.example.developer.rssreader.view.FeedItemViewHolder
import java.lang.ref.WeakReference


class FeedAdapter(listener: ItemClickListener?, items: List<FeedEntry>?) : BaseAdapter<FeedItemViewHolder, FeedEntry>(listener, items) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int, listener: WeakReference<ItemClickListener>?): FeedItemViewHolder {
        return FeedItemViewHolder(parent, listener)
    }

    override fun onBindViewHolder(holder: FeedItemViewHolder, position: Int, item: FeedEntry) {
        holder.tvTitle.text = item.title
    }
}