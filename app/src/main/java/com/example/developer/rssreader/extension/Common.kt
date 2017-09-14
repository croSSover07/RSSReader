package com.example.developer.rssreader.extension

import java.lang.ref.WeakReference


fun <T> T.weak() = WeakReference(this)