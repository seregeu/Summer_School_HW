package com.example.summer_school_hw.view

import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.summer_school_hw.R
import com.example.summer_school_hw.view.fragments.UserInfoFragment
import com.example.summer_school_hw.ui.main.MovieListFragment
import com.example.summer_school_hw.viewmodel.MainViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity(){


    val MAIN_FRAGMENT_TAG = "MainFragment"
    val BACK_STACK_ROOT_TAG = "RootFragment"

    //for preserving the state of a fragment upon destruction
    var genreSave: Int = -1
    var moviePosition: Int = -1
    var isListUpdated: Boolean=false
    companion object {
        private const val MOVIE_ARGUMENT = "MovieArgument"
        private const val MOVIE_LIST_UPDATED = "MovieListUpd"
        private const val MOVIE_SELECTED = "MovieSelected"
    }

    private val mainViewModel: MainViewModel by viewModels()

    lateinit var mainFragment:MovieListFragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //subscribe to genreList


        if (savedInstanceState == null) {
            mainFragment = MovieListFragment.newInstance()
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, mainFragment, MAIN_FRAGMENT_TAG)
                .commit()
        }else {
            val fragmentTransaction: FragmentTransaction = supportFragmentManager.beginTransaction()
            val fragment: Fragment = supportFragmentManager.findFragmentByTag(MAIN_FRAGMENT_TAG) as MovieListFragment
            if (fragment != null) {
                fragmentTransaction.remove(fragment)
                fragmentTransaction.commit()
            }
            genreSave = savedInstanceState.getInt(MOVIE_ARGUMENT)
            isListUpdated = savedInstanceState.getBoolean(MOVIE_LIST_UPDATED)
            moviePosition = savedInstanceState.getInt(MOVIE_SELECTED)
            mainFragment = MovieListFragment.newInstance()
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, mainFragment, MAIN_FRAGMENT_TAG)
                .commit()
        }
        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        navView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.nav_home -> {
                    supportFragmentManager.beginTransaction()
                        .replace(
                            R.id.fragment_container, MovieListFragment.newInstance())
                        .commit()
                }
                R.id.nav_profile -> {
                    supportFragmentManager.beginTransaction().replace(
                        R.id.fragment_container,
                        UserInfoFragment.newInstance()).addToBackStack(BACK_STACK_ROOT_TAG).commit()
                }
                else -> null
            } != null
        }


    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(MOVIE_ARGUMENT, genreSave)
        outState.putBoolean(MOVIE_LIST_UPDATED, isListUpdated)
        outState.putInt(MOVIE_SELECTED, moviePosition)
    }

    override fun onBackPressed() {
        mainViewModel.selectMovie(-1)
        super.onBackPressed()
    }
}