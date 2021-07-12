package com.example.summer_school_hw

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.example.summer_school_hw.R.id.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)

        val navView: BottomNavigationView = findViewById(nav_view)
        val recyclerView: RecyclerView = findViewById(R.id.rv_actors)
            //crutch with adding actors to memory
        val actors = listOf(
            Actor(R.drawable.image_actor_jason_stateham,"Джейсон Стэйтем"),
            Actor(R.drawable.image_actor_holt_mccallany,"Холт Маккэллани"),
            Actor(R.drawable.image_actor_josh_hartnett,"Джош Харнетт")
        )
            //transferring data to the adapter
        recyclerView.adapter = CustomRecyclerAdapter(actors)

        navView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                nav_home -> Log.i("menu","tapped_home" )
                nav_profile -> Log.i("menu","tapped_profile" )
                else -> null
            } != null
        }
    }
}




