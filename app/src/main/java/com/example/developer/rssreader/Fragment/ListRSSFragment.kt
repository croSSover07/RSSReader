package com.example.developer.rssreader.Fragment

import android.app.Fragment
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.developer.rssreader.Adapter.FeedAdapter2
import com.example.developer.rssreader.DataHelper
import com.example.developer.rssreader.Model.Entry
import com.example.developer.rssreader.R
import kotlinx.android.synthetic.main.fragment_list_rss.*


/**
 * Created by developer on 11.09.17.
 */
class ListRSSFragment :Fragment() {



    override fun onCreateView(inflater: LayoutInflater , container: ViewGroup, savedInstanceState: Bundle?): View {
        val view=inflater.inflate(R.layout.fragment_list_rss,container,false)


        return view
     }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        val linearLayoutManager= LinearLayoutManager( activity.applicationContext, LinearLayoutManager.VERTICAL,false)
        recyclerView.layoutManager=linearLayoutManager
        refreshItems()
        swiperefreshlayout.setOnRefreshListener(SwipeRefreshLayout.OnRefreshListener {
            refreshItems()
        })


        super.onViewCreated(view, savedInstanceState)
    }

    private fun refreshItems() {
        val dataHelper=DataHelper()

        onItemsLoadComplete(dataHelper.GetListEntry());
    }

    fun onItemsLoadComplete( list:List<Entry>) {
        val adapter= FeedAdapter2(list,activity)
        recyclerView.adapter=adapter
        adapter.notifyDataSetChanged()
        swiperefreshlayout.setRefreshing(false)
    }


 



}