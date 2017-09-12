package com.example.developer.rssreader.Model

/**
 * Created by developer on 11.09.17.
 */

data class Item(val title:String,
                val pubDate:String,
                val link:String,
                val guid:String,
                val author:String,
                val thumbnail:String,
                val description:String,
                val content:String,
                val enclosure:List<Any>,
                val categories:List<String> )
