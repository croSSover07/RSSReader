package com.example.developer.rssreader

import android.app.Fragment
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.developer.rssreader.Fragment.ListRSSFragment


class MainActivity : AppCompatActivity() {




    private var listRSSFragment: ListRSSFragment?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if(savedInstanceState==null) {
            listRSSFragment = Fragment.instantiate(this@MainActivity, ListRSSFragment::class.java!!.getName()) as ListRSSFragment
            fragmentManager.beginTransaction()
                    .replace(R.id.frame_layout, listRSSFragment)
                    .commit()
        }else{
            // do nothing - fragment is recreated automatically
        }


    }


}
