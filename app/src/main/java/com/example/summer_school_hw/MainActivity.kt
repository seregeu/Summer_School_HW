package com.example.summer_school_hw

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.summer_school_hw.ui.main.MovieListFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, MovieListFragment.newInstance())
                .commitNow()
        }
    }
}