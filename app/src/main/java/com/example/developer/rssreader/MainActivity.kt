package com.example.developer.rssreader

import android.app.Fragment
import android.app.ProgressDialog
import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.example.developer.rssreader.Adapter.FeedAdapter
import com.example.developer.rssreader.Fragment.ListRSSFragment
import com.example.developer.rssreader.Model.RSSRoot
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_list_rss.*

class MainActivity : AppCompatActivity() {

    private val RSS_link="https://www.reddit.com/r/Kotlin/.rss";
    private val RSS_to_JSON_API=" https://api.rss2json.com/v1/api.json?rss_url=";

    private var listRSSFragment: ListRSSFragment?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listRSSFragment=Fragment.instantiate(this@MainActivity,ListRSSFragment::class.java!!.getName()) as ListRSSFragment

        fragmentManager.beginTransaction().replace(R.id.frame_layout, listRSSFragment).commit()

    }


}
