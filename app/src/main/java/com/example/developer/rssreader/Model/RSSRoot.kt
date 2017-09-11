package com.example.developer.rssreader.Model

/**
 * Created by developer on 11.09.17.
 */
//public string status { get; set; }
//public Feed feed { get; set; }
//public List<Item> items { get; set; }
data class RSSRoot(val status:String,val feed:Feed,val items:List<Item>){
    companion object

}