package com.example.developer.rssreader

import android.util.Xml
import com.example.developer.rssreader.Model.Entry
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserException
import java.io.IOException
import java.io.InputStream
import java.util.*

/**
 * Created by developer on 13.09.17.
 */
class CurrentXmlParser {
    private val ns: String? = null

    @Throws(XmlPullParserException::class, IOException::class)
    fun parse(`in`: InputStream): List<Entry> {
        try {
            val parser = Xml.newPullParser()
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false)
            parser.setInput(`in`, null)
            parser.nextTag()
            return readFeed(parser)
        } finally {
            `in`.close()
        }
    }

    @Throws(XmlPullParserException::class, IOException::class)
    private fun readFeed(parser: XmlPullParser): List<Entry> {
        val entries = ArrayList<Entry>()

        parser.require(XmlPullParser.START_TAG, ns, "feed")
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
    private fun readEntry(parser: XmlPullParser): Entry {
        parser.require(XmlPullParser.START_TAG, ns, "entry")
        var title: String? = null
        var content: String? = null
        var link: String? = null
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.eventType != XmlPullParser.START_TAG) {
                continue
            }
            val name = parser.name
            if (name == "title") {
                title = readTitle(parser)
            } else if (name == "content") {
                content = readContent(parser)
            } else if (name == "link") {
                link = readLink(parser)
            } else {
                skip(parser)
            }
        }
        return Entry(title,  link,content)
    }

    @Throws(IOException::class, XmlPullParserException::class)
    private fun readContent(parser: XmlPullParser): String {
        parser.require(XmlPullParser.START_TAG, ns, "content")
        val title = readText(parser)
        parser.require(XmlPullParser.END_TAG, ns, "content")
        return title
    }
    @Throws(IOException::class, XmlPullParserException::class)
    private fun readTitle(parser: XmlPullParser): String {
        parser.require(XmlPullParser.START_TAG, ns, "title")
        val title = readText(parser)
        parser.require(XmlPullParser.END_TAG, ns, "title")
        return title
    }

    // Processes link tags in the feed.
    @Throws(IOException::class, XmlPullParserException::class)
    private fun readLink(parser: XmlPullParser): String {
        var link = ""
        parser.require(XmlPullParser.START_TAG, ns, "link")
        val tag = parser.name
        if (tag == "link") {
            link = parser.getAttributeValue(null, "href")
                 parser.nextTag()
        }
        parser.require(XmlPullParser.END_TAG, ns, "link")
        return link
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