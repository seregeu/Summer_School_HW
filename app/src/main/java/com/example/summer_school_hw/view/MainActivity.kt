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
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.summer_school_hw.R
import com.example.summer_school_hw.view.fragments.UserInfoFragment
import com.example.summer_school_hw.ui.main.MovieListFragment
import com.example.summer_school_hw.viewmodel.MainViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity(){

    //for preserving the state of a fragment upon destruction
    companion object {
        private const val MOVIE_ARGUMENT = "MovieArgument"
        private const val MOVIE_LIST_UPDATED = "MovieListUpd"
        private const val MOVIE_SELECTED = "MovieSelected"
    }
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navController = Navigation.findNavController(this, R.id.fragment_container)

        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        navView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.MovieListFragment -> {
                    navController.navigate(R.id.movieListFragment)
                }
                R.id.UserInfoFragment -> {
                    navController.navigate(R.id.userInfoFragment)
                }
                else -> null
            } != null
        }
    }
}