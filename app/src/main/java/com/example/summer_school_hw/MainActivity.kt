package com.example.summer_school_hw

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.summer_school_hw.fragments.UserInfoFragment
import com.example.summer_school_hw.ui.main.MovieDetailsFragment
import com.example.summer_school_hw.ui.main.MovieListFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, MovieListFragment.newInstance())
                .commitNow()
        }
        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        navView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.nav_home -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, MovieListFragment.newInstance())
                        .commitNow()
                }
                R.id.nav_profile -> {
                    val BACK_STACK_ROOT_TAG = "root_fragment"
                    supportFragmentManager.beginTransaction().replace(
                        R.id.fragment_container,
                        UserInfoFragment.newInstance()).addToBackStack(BACK_STACK_ROOT_TAG).commit()
                }
                else -> null
            } != null
        }
    }



}