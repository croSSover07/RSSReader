package com.example.developer.rssreader

import android.os.AsyncTask
import com.example.developer.rssreader.model.deprecated.Entry
import com.example.developer.rssreader.parser.FeedXMLParser
import java.io.IOException
import java.io.InputStream
import java.io.Serializable
import java.net.HttpURLConnection
import java.net.URL


@Deprecated(message = "Invalid usage of AsyncTask")
class DataHelper:Serializable {
        //private val  urlString ="https://www.reddit.com/r/Kotlin/.rss"
        private val  urlString ="https://www.reddit.com/r/news/new/.rss"
      var listEntry:List<Entry>?=null
    fun GetListEntry():List<Entry>?{
        val asyncTask=object : AsyncTask<Void, Void, List<Entry>>(){
            override fun doInBackground(vararg p0: Void?): List<Entry>? {
                val stream = downloadUrl(urlString )
                val currentXmlParser= FeedXMLParser()
//                val root=currentXmlParser.parse(stream)
//                return root
                return null
            }


        }
//        TODO: Что это вообще? для чего использовать AsyncTask, если ты вызываешь блокируюющий вызов get(), что соответственно будет все равно выполнять операцию doInBackground в основном потоке.
//        Точнее она будет выполняться то в фоновом потоке, но основной будет заблокирован и будет ожидать выполнения операции.
        listEntry=asyncTask.execute().get()
       return  listEntry
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