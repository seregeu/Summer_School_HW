package com.example.summer_school_hw

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.summer_school_hw.fragments.UserInfoFragment
import com.example.summer_school_hw.ui.main.MovieListFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity(), MovieListFragment.OnMovieLisChangeListener,
    MovieListFragment.OnMovieListUpdateListener, MovieListFragment.OnMovieitemTapedListener{
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
    lateinit var mainFragment:MovieListFragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            mainFragment = MovieListFragment.newInstance(this,this,this,
                genreSave,isListUpdated,moviePosition)
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
            mainFragment = MovieListFragment.newInstance(this,this,this,
                genreSave,isListUpdated,moviePosition)
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, mainFragment, MAIN_FRAGMENT_TAG)
                .commit()
        }
        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        navView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.nav_home -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, MovieListFragment.newInstance(this,
                            this,this,
                            genreSave,isListUpdated,moviePosition))
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

    override fun onMovieListChanged(genrePosition: Int) {
        genreSave = genrePosition
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(MOVIE_ARGUMENT, genreSave)
        outState.putBoolean(MOVIE_LIST_UPDATED, isListUpdated)
        outState.putInt(MOVIE_SELECTED, moviePosition)
    }

    override fun onBackPressed() {
        moviePosition=-1;
        super.onBackPressed()
    }

    override fun OnMovieListUpdated() {
        isListUpdated = true
    }

    override fun OnMovieSelected(_moviePosition: Int) {
        moviePosition=_moviePosition
    }


}