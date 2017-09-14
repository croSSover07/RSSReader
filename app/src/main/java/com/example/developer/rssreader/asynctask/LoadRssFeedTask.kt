package com.example.developer.rssreader.asynctask

import android.os.AsyncTask
import com.example.developer.rssreader.extension.weak
import com.example.developer.rssreader.model.FeedEntry
import com.example.developer.rssreader.parser.FeedXMLParser
import java.io.IOException
import java.lang.ref.WeakReference
import java.net.HttpURLConnection
import java.net.URL


class LoadRssFeedTask(listener: OnFeedLoadedListener) : AsyncTask<String, Void, List<FeedEntry>>() {

//    TODO: WeakReference используется что бы не было утечек памяти. Поскольку нашим listener`ом является Fragment, и время его жизни ограничено, нельзя хранить жесткую ссылку на него
//    поскольку система может убить его, но GC не сможет освободить его память так фрагмент будет ссылаться на инстанс данного AsyncTask который в свою очередь будет хранить ссылку
//    на фрагмент (так как наш фрагмент реализует интерфейс OnFeedLoadedListener).

    private val listener: WeakReference<OnFeedLoadedListener> = listener.weak()

    override fun doInBackground(vararg params: String?): List<FeedEntry>? {
        try {
//            TODO: Проверка на наличие параметров.
            if (params.isEmpty()) return null

            val url = URL(params[0])
            val connection = url.openConnection() as? HttpURLConnection ?: return null

            connection.readTimeout = 10000
            connection.connectTimeout = 15000
            connection.requestMethod = "GET"
            connection.doInput = true
            connection.connect()

            val inputStream = connection.inputStream ?: return null

            return FeedXMLParser.parse(inputStream)
        } catch (ex: IOException) {
            return null
        }
    }

    override fun onCancelled(result: List<FeedEntry>?) {
        listener.get()?.onFeedLoaded(result)
    }

    override fun onPostExecute(result: List<FeedEntry>?) {
        listener.get()?.onFeedLoaded(result)
    }

    interface OnFeedLoadedListener {
        fun onFeedLoaded(feed: List<FeedEntry>?)
    }
}