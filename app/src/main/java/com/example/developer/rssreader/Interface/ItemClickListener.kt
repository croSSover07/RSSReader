package com.example.developer.rssreader.Interface

import android.view.View

/**
 * Created by developer on 11.09.17.
 */
interface ItemClickListener {
    fun onClick (  view:View, position:  Int, isLongClick:Boolean)
}