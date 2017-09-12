package com.example.developer.rssreader

import android.app.Fragment

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

import com.example.developer.rssreader.Fragment.ListRSSFragment


class MainActivity : AppCompatActivity() {



    private var listRSSFragment: ListRSSFragment?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listRSSFragment=Fragment.instantiate(this@MainActivity,ListRSSFragment::class.java!!.getName()) as ListRSSFragment

        fragmentManager.beginTransaction()
                .replace(R.id.frame_layout, listRSSFragment)
                .addToBackStack("listFragment")
                .commit()


    }


}
