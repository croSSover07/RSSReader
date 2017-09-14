package com.example.developer.rssreader.Fragment

import android.app.Fragment
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.developer.rssreader.Adapter.FeedAdapter
import com.example.developer.rssreader.DataHelper
import com.example.developer.rssreader.Model.Entry
import com.example.developer.rssreader.R
import kotlinx.android.synthetic.main.fragment_list_rss.*


/**
 * Created by developer on 11.09.17.
 */
class ListRSSFragment :Fragment() {

    private val TAG="ListRSSFragment"
    private val KEY="datahelper"
    private var dataHelper:DataHelper
    init {
        dataHelper= DataHelper()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.i(TAG,"onCreate")
        super.onCreate(savedInstanceState)

        if(savedInstanceState!=null) {
            dataHelper=savedInstanceState[KEY] as DataHelper
        }
        else{

        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.i(TAG,"onSaveInstanceState")
        outState.putSerializable(KEY,dataHelper)
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        Log.i(TAG,"onViewStateRestored")
        if(savedInstanceState!=null)
        {
            dataHelper=savedInstanceState[KEY] as DataHelper
        }
        super.onViewStateRestored(savedInstanceState)
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup, savedInstanceState: Bundle?): View {
        Log.i(TAG,"onCreateView")
        val view=inflater.inflate(R.layout.fragment_list_rss,container,false)
        return view
     }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        Log.i(TAG,"onViewCreated")
        val linearLayoutManager= LinearLayoutManager( activity.applicationContext, LinearLayoutManager.VERTICAL,false)
        recyclerView.layoutManager=linearLayoutManager

        if (dataHelper.listEntry==null){
            refreshItems()
        }
        else{
            onItemsLoadComplete(dataHelper.listEntry)
        }

        swiperefreshlayout.setOnRefreshListener(SwipeRefreshLayout.OnRefreshListener {
            refreshItems()
        })
        super.onViewCreated(view, savedInstanceState)
    }

    private fun refreshItems() {
        Log.i(TAG,"-----------refreshItems")
        onItemsLoadComplete(dataHelper.GetListEntry())
    }

    fun onItemsLoadComplete( list:List<Entry>?) {
        Log.i(TAG,"-----------onItemsLoadComplete")
        val adapter= FeedAdapter(list,activity)
        recyclerView.adapter=adapter
        adapter.notifyDataSetChanged()
        swiperefreshlayout.setRefreshing(false)
    }






}