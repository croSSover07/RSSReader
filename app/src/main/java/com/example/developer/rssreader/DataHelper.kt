package com.example.developer.rssreader

import android.os.AsyncTask
import com.example.developer.rssreader.Model.Entry
import java.io.IOException
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL


/**
 * Created by developer on 12.09.17.
 */
class DataHelper {
        //private val  urlString ="https://www.reddit.com/r/Kotlin/.rss"
        private val  urlString ="https://www.reddit.com/r/news/new/.rss"

    fun GetListEntry():List<Entry>{
        val asyncTask=object : AsyncTask<Void, Void, List<Entry>>(){
            override fun doInBackground(vararg p0: Void?): List<Entry>? {
                val stream = downloadUrl(urlString )
                val currentXmlParser= CurrentXmlParser()
                val root=currentXmlParser.parse(stream)
                return root
            }


        }
       return asyncTask.execute().get()
    }
    @Throws(IOException::class)
    private fun downloadUrl(urlString: String): InputStream {
        val url = URL(urlString)
        val conn = url.openConnection() as HttpURLConnection
        conn.setReadTimeout(10000 /* milliseconds */)
        conn.setConnectTimeout(15000 /* milliseconds */)
        conn.setRequestMethod("GET")
        conn.setDoInput(true)
        conn.connect()
        return conn.getInputStream()
    }


}