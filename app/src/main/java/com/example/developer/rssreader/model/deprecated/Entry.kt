package com.example.developer.rssreader.model.deprecated

import java.io.Serializable

@Deprecated(message = "Deprecated")
class Entry(val title:String?, val link:String?, val content:String?):Serializable

