package com.wonmirzo.activity

import android.os.Bundle
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.wonmirzo.R
import com.wonmirzo.fragment.FilterFragment
import com.wonmirzo.fragment.ImageChooserFragment
import com.wonmirzo.fragment.PhotoFragment
import com.wonmirzo.fragment.SearchFragment
import com.wonmirzo.model.FilterItem
import com.wonmirzo.network.RetrofitHttp
import com.wonmirzo.network.model.UploadItem
import com.wonmirzo.utils.Logger
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(), FilterFragment.FilterSend{
    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var bottomAppBar: BottomAppBar
    private lateinit var flFragment: FrameLayout
    private lateinit var fab: FloatingActionButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()
    }

    private fun initViews() {
        bottomAppBar = findViewById(R.id.bottomAppBar)
        bottomNavigationView = findViewById(R.id.bottomNavigationView)
        bottomNavigationView.background = null
        bottomNavigationView.menu.getItem(1).isEnabled = false

        fab = findViewById(R.id.fab)

        fab.setOnClickListener {
            chooseImage()
        }

        flFragment = findViewById(R.id.flFragment)
        val searchFragment = SearchFragment()
        val photoFragment = PhotoFragment()
        selectCurrentFragment(searchFragment)
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.search -> {
                    selectCurrentFragment(searchFragment)
                }
                R.id.images -> {
                    selectCurrentFragment(photoFragment)
                }
            }
            true
        }
    }

    private fun chooseImage() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.flFragment, ImageChooserFragment())
            .addToBackStack(null)
            .commit()
    }

    private fun selectCurrentFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.flFragment, fragment)
            .commit()
    }

    override fun onFilterSend(filter: FilterItem) {
        Logger.d("@@@", filter.toString())
    }
}