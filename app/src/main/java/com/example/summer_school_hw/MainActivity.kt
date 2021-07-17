package com.example.summer_school_hw

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.example.summer_school_hw.R.id.*
import com.example.summer_school_hw.data.dto.ActorDto
import com.example.summer_school_hw.data.features.actors.ActorsDataSourceImpl
import com.example.summer_school_hw.data.presentation.ActorsModel

class MainActivity : AppCompatActivity() {
    private lateinit var actorsModel: ActorsModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)

        //init data
        initDataSource()
        val navView: BottomNavigationView = findViewById(nav_view)
        val recyclerView: RecyclerView = findViewById(R.id.rv_actors)
        val actors = actorsModel.getActors()
            //transferring data to the adapter
        recyclerView.adapter = ActorRecyclerAdapter(actors)

        navView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                nav_home -> {
                    val intent = Intent(this, activity_movies_list::class.java)
                    startActivity(intent)
                }
                nav_profile -> Log.i("menu","tapped_profile" )
                else -> null
            } != null
        }

    }
    private fun initDataSource() {
        actorsModel = ActorsModel(ActorsDataSourceImpl())
    }
}




