package com.example.developer.rssreader.parser

import android.util.Xml
import com.example.developer.rssreader.model.FeedEntry
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserException
import java.io.IOException
import java.io.InputStream
import java.util.*


//    TODO: Именования классов должны быть осмысленными, что бы другим разработчикам было легче понимать "что?" и "для чего?".
class FeedXMLParser {

    //    TODO: Неь необходимости создавать инстанс парсера, его методы могут быть статическими.
    companion object {
        private val NAMESPACE: String? = null

        @Throws(XmlPullParserException::class, IOException::class)
        fun parse(stream: InputStream): List<FeedEntry> {
            stream.use {
                val parser = Xml.newPullParser()
                parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false)
                parser.setInput(it, null)
                parser.nextTag()
                return readFeed(parser)
            }
        }

        @Throws(XmlPullParserException::class, IOException::class)
        private fun readFeed(parser: XmlPullParser): List<FeedEntry> {
            val entries = ArrayList<FeedEntry>()

            parser.require(XmlPullParser.START_TAG, NAMESPACE, "feed")
            while (parser.next() != XmlPullParser.END_TAG) {
                if (parser.eventType != XmlPullParser.START_TAG) {
                    continue
                }
                val name = parser.name
                // Starts by looking for the entry tag
                if (name == "entry") {
                    entries.add(readEntry(parser))
                } else {
                    skip(parser)
                }
            }
            return entries
        }

        @Throws(XmlPullParserException::class, IOException::class)
        private fun readEntry(parser: XmlPullParser): FeedEntry {
            parser.require(XmlPullParser.START_TAG, NAMESPACE, "entry")
            var title: String? = null
            var content: String? = null
            while (parser.next() != XmlPullParser.END_TAG) {
                if (parser.eventType != XmlPullParser.START_TAG) {
                    continue
                }
                val name = parser.name
                when (name) {
                    "title" -> title = readTitle(parser)
                    "content" -> content = readContent(parser)
                    else -> skip(parser)
                }
            }
            return FeedEntry(title, content)
        }

        @Throws(IOException::class, XmlPullParserException::class)
        private fun readContent(parser: XmlPullParser): String {
            parser.require(XmlPullParser.START_TAG, NAMESPACE, "content")
            val title = readText(parser)
            parser.require(XmlPullParser.END_TAG, NAMESPACE, "content")
            return title
        }

        @Throws(IOException::class, XmlPullParserException::class)
        private fun readTitle(parser: XmlPullParser): String {
            parser.require(XmlPullParser.START_TAG, NAMESPACE, "title")
            val title = readText(parser)
            parser.require(XmlPullParser.END_TAG, NAMESPACE, "title")
            return title
        }


        @Throws(IOException::class, XmlPullParserException::class)
        private fun readText(parser: XmlPullParser): String {
            var result = ""
            if (parser.next() == XmlPullParser.TEXT) {
                result = parser.text
                parser.nextTag()
            }
            return result
        }

        @Throws(XmlPullParserException::class, IOException::class)
        private fun skip(parser: XmlPullParser) {
            if (parser.eventType != XmlPullParser.START_TAG) {
                throw IllegalStateException()
            }
            var depth = 1
            while (depth != 0) {
                when (parser.next()) {
                    XmlPullParser.END_TAG -> depth--
                    XmlPullParser.START_TAG -> depth++
                }
            }
        }
    }
}