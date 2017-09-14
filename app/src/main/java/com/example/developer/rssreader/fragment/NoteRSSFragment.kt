package com.example.developer.rssreader.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.developer.rssreader.R
import com.example.developer.rssreader.model.FeedEntry
import kotlinx.android.synthetic.main.fragment_note_rss.*


class NoteRSSFragment : Fragment() {

    companion object {
        private const val KEY_FEED_ENTRY = "key_feed_entry"

        fun newInstance(feedEntry: FeedEntry): NoteRSSFragment {
            val arguments = Bundle()
            arguments.putParcelable(KEY_FEED_ENTRY, feedEntry)

            val fragment = NoteRSSFragment()
            fragment.arguments = arguments
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_note_rss, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val feedEntry: FeedEntry = arguments.getParcelable(KEY_FEED_ENTRY)

        textView_title_note.text = feedEntry.title
        @Suppress("DEPRECATION")
        textView_content_note.text = Html.fromHtml(feedEntry.content)

//        if(arguments.getString("entry")!=null) {
//            val entry: Entry
//            entry= Gson().fromJson(arguments.getString("entry"), Entry::class.java!!)
//            textView_title_note.setText( entry.title)
//            textView_content_note.setText( entry.content)
//        }
    }


}