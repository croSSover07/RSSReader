package com.example.developer.rssreader.Fragment

import android.app.Fragment
import android.os.AsyncTask
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.developer.rssreader.Adapter.FeedAdapter
import com.example.developer.rssreader.DataHelper

import com.example.developer.rssreader.Model.RSSRoot
import com.example.developer.rssreader.R
import kotlinx.android.synthetic.main.fragment_list_rss.*



/**
 * Created by developer on 11.09.17.
 */
class ListRSSFragment :Fragment(){
    //private val RSS_link="https://www.reddit.com/r/Kotlin/.rss";
    private val RSS_link="https://www.reddit.com/r/news/.rss";

    private val RSS_to_JSON_API=" https://api.rss2json.com/v1/api.json?rss_url=";

    override fun onCreateView(inflater: LayoutInflater , container: ViewGroup, savedInstanceState: Bundle?): View {
        val view=inflater.inflate(R.layout.fragment_list_rss,container,false)


        return view
     }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        val linearLayoutManager= LinearLayoutManager( activity.applicationContext, LinearLayoutManager.VERTICAL,false)
        recyclerView.layoutManager=linearLayoutManager
        loadRSSToRecycler()
        super.onViewCreated(view, savedInstanceState)
    }

    private fun loadRSSToRecycler(){
       val asyncTask=object : AsyncTask<Void,Void,RSSRoot>(){
           override fun doInBackground(vararg p0: Void?): RSSRoot? {
               val dataHelper=DataHelper()
               val  rssRoot=dataHelper.GetRSSRoot()
               return rssRoot
           }

           override fun onPostExecute(rssRoot: RSSRoot) {
               val adapter= FeedAdapter(rssRoot,activity)
               recyclerView.adapter=adapter
               adapter.notifyDataSetChanged()
           }
       }
        asyncTask.execute()

    }


}