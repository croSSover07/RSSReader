package com.example.developer.rssreader.view

import android.view.ViewGroup
import android.widget.TextView
import com.example.developer.rssreader.R
import com.example.developer.rssreader.adapter.BaseAdapter
import java.lang.ref.WeakReference


class FeedItemViewHolder(
        parent: ViewGroup,
        listener: WeakReference<BaseAdapter.ItemClickListener>?
) : BaseAdapter.ViewHolder(parent, R.layout.layout_row, listener) {
    val tvTitle: TextView = itemView.findViewById(R.id.textView_title_row)
}