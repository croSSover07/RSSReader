package com.example.developer.rssreader.activity

import android.os.Bundle
import android.support.annotation.IdRes
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem


abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportFragmentManager.addOnBackStackChangedListener {
            supportActionBar?.setDisplayHomeAsUpEnabled(supportFragmentManager.backStackEntryCount != 0)
        }
    }

    fun fragmentInContainer(containerId: Int): Fragment? = supportFragmentManager.findFragmentById(containerId)

    fun replaceFragment(@IdRes containerId: Int, fragment: Fragment, addToBackStack: Boolean = false, backStackName: String? = null) {

        val transaction = supportFragmentManager.beginTransaction().replace(containerId, fragment)
        if (addToBackStack) {
            transaction.addToBackStack(backStackName)
        }
        transaction.commit()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}