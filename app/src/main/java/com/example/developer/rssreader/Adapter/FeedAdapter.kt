package com.example.developer.rssreader.Adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.developer.rssreader.Interface.ItemClickListener
import com.example.developer.rssreader.Model.RSSRoot
import com.example.developer.rssreader.R

/**
 * Created by developer on 11.09.17.
 */
class FeedAdapter(private val RSSRoot:RSSRoot,private val mContext:Context):RecyclerView.Adapter<FeedViewHolder>(){

    private val inflater: LayoutInflater

    init{
        inflater= LayoutInflater.from(mContext)
    }

    override fun onBindViewHolder(holder: FeedViewHolder, position: Int) {
        holder.txtTitle.text=RSSRoot.items[position].title

        //add onClick
    }

    override fun getItemCount(): Int {
        return RSSRoot.items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): FeedViewHolder {
        val itemView=inflater.inflate(R.layout.layout_row,parent,false)
        return FeedViewHolder(itemView)
    }
}

class FeedViewHolder(itemView: View):RecyclerView.ViewHolder(itemView),View.OnClickListener,View.OnLongClickListener {

    var txtTitle:TextView

     private var itemClickListener:ItemClickListener?=null

    init{
        txtTitle=itemView.findViewById(R.id.textView_title)

        itemView.setOnClickListener(this)
        itemView.setOnLongClickListener(this)

    }
    fun setItemClickListener(itemClickListener :ItemClickListener) {
        this.itemClickListener=itemClickListener
    }

    override fun onClick(v: View) {
         itemClickListener!!.onClick(v,adapterPosition,false)
    }

    override fun onLongClick(v: View ): Boolean {
        itemClickListener!!.onClick(v,adapterPosition,true)
        return true
    }
}

