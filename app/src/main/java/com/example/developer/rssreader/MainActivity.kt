package com.example.developer.rssreader

import android.os.Bundle
import android.support.v4.app.Fragment
import com.example.developer.rssreader.activity.BaseActivity
import com.example.developer.rssreader.fragment.ListRSSFragment


class MainActivity : BaseActivity() {

//    private var listRSSFragment: ListRSSFragment? = null
// TODO: нет необходимости хранить ссылку на фрагмент, так как мы его не используем в дальнейшем.

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (fragmentInContainer(R.id.frame_layout) == null) {
            replaceFragment(R.id.frame_layout, ListRSSFragment())
        }
    }

    fun replaceMainFragment(fragment: Fragment, addToBackStack: Boolean = false, backStackName: String? = null) {
        replaceFragment(R.id.frame_layout, fragment, addToBackStack, backStackName)
    }

}
