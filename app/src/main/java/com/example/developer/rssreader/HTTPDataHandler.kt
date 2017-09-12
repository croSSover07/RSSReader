package com.example.developer.rssreader

import java.io.BufferedInputStream
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

/**
 * Created by developer on 11.09.17.
 */
class HTTPDataHandler {
    fun GetHTTPHandler(urlString: String): String? {
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

    companion object {
        internal var stream = ""
    }


}