package com.example.developer.rssreader


import com.example.developer.rssreader.Model.RSSRoot
import com.google.gson.Gson
import java.io.BufferedInputStream
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

/**
 * Created by developer on 12.09.17.
 */
class DataHelper {

    private val RSS_link="https://www.reddit.com/r/news/.rss";

    private val RSS_to_JSON_API=" https://api.rss2json.com/v1/api.json?rss_url=";

    private fun GetHTTP (urlString: String): String? {
        var stream=""
        try {

            val url = URL(urlString)
            val urlConnection = url.openConnection() as HttpURLConnection
            if (urlConnection.responseCode == HttpURLConnection.HTTP_OK) {
                val inputStream = BufferedInputStream(urlConnection.inputStream)
                val r = BufferedReader(InputStreamReader(inputStream))
                val sb = StringBuilder()
                var line = r.readLine()
                while (line   != null) {
                    sb.append(line)
                    stream = sb.toString()
                    urlConnection.disconnect()
                    line=r.readLine()
                }
            }

        } catch (ex: Exception) {
            return null
        }

        return stream
    }

     fun GetRSSRoot():RSSRoot {

         val url_get_data=StringBuilder(RSS_to_JSON_API)
         url_get_data.append(RSS_link)
         val result:String?
         val http=DataHelper()
         result=http.GetHTTP(url_get_data.toString())
         var rssRoot: RSSRoot
         rssRoot= Gson().fromJson<RSSRoot>(result, RSSRoot::class.java!!)
         return  rssRoot
    }

}