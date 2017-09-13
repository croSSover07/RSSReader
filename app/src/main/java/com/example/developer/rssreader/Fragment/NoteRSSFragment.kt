package com.example.developer.rssreader.Fragment

import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.developer.rssreader.Model.Entry

import com.example.developer.rssreader.R
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_note_rss.*


/**
 * Created by developer on 12.09.17.
 */
class NoteRSSFragment: Fragment(){


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view=inflater.inflate(R.layout.fragment_note_rss,container,false)
        return view
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {

        if(arguments.getString("entry")!=null) {
            val entry: Entry
            entry= Gson().fromJson(arguments.getString("entry"), Entry::class.java!!)
            textView_title_note.setText( entry.title)
            textView_content_note.setText( entry.content)
        }
        super.onViewCreated(view, savedInstanceState)
    }


}