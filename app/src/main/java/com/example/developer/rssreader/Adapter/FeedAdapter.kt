package com.example.developer.rssreader.Adapter

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.developer.rssreader.Fragment.NoteRSSFragment
import com.example.developer.rssreader.Interface.ItemClickListener
import com.example.developer.rssreader.Model.Entry
import com.example.developer.rssreader.R
import com.google.gson.Gson

/**
 * Created by developer on 11.09.17.
 */
class FeedAdapter(private val listEntry:List<Entry>?, private val mContext:Context):RecyclerView.Adapter<FeedViewHolder>(){
    private val TAG="FeedAdapter"
    private val inflater: LayoutInflater

    init{
        inflater= LayoutInflater.from(mContext)
    }

    override fun onBindViewHolder(holder: FeedViewHolder, position: Int) {

        holder.txtTitle.text= listEntry!![position].title


        holder.setItemClickListener(ItemClickListener{view,position,isLongClick ->
            if(!isLongClick){
                //new fragment
                Log.i(TAG,"click")
                val rssRootGson= Gson().toJson(listEntry[position],Entry::class.java!!)
                var bundle = Bundle()
                bundle.putString("entry",rssRootGson)
                val noteRSSFragment = NoteRSSFragment()
                noteRSSFragment.arguments=bundle
                val fragmentManager= (mContext as Activity).fragmentManager
                val fragmentTransaction = fragmentManager.beginTransaction()
                fragmentTransaction.replace(R.id.frame_layout, noteRSSFragment )
                fragmentTransaction.addToBackStack("list")
                fragmentTransaction.commit()
            }
        })
    }

    override fun getItemCount(): Int = listEntry!!.size

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): FeedViewHolder {
        val itemView=inflater.inflate(R.layout.layout_row,parent,false)
        return FeedViewHolder(itemView)
    }
}

class FeedViewHolder(itemView: View):RecyclerView.ViewHolder(itemView),View.OnClickListener,View.OnLongClickListener {

    var txtTitle:TextView

    private var itemClickListener:ItemClickListener?=null

    init{
        txtTitle=itemView.findViewById(R.id.textView_title_row)

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

