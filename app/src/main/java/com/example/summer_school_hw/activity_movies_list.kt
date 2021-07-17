package com.example.summer_school_hw

import android.graphics.Rect
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.summer_school_hw.data.features.actors.ActorsDataSourceImpl
import com.example.summer_school_hw.data.features.movies.MoviesDataSourceImpl
import com.example.summer_school_hw.data.presentation.ActorsModel
import com.example.summer_school_hw.data.presentation.MoviesModel

class activity_movies_list : AppCompatActivity() {
    private lateinit var moviesModel: MoviesModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies_list)

        //temp
        val genres: List<String>
        genres = listOf("Боевики", "Драмы", "Комедии","ОченьДлинноеНазваниеЖанра","ОченьДлинноеНазваниеЖанра2","ОченьДлинноеНазваниеЖанра3")
        val recyclerViewGenres: RecyclerView = findViewById(R.id.rv_genres)
        recyclerViewGenres.adapter = GenreRecyclerAdapter(genres)

        initDataSource()
        // initialize a mutable list of animals
        val movies = moviesModel.getMovies()
        val recyclerViewMovies: RecyclerView = findViewById(R.id.rv_movies_list)
        // initialize grid layout manager
        GridLayoutManager(
            this, // context
            2, // span count
            RecyclerView.VERTICAL, // orientation
            false // reverse layout
        ).apply {
            // specify the layout manager for recycler view
            recyclerViewMovies.layoutManager = this
        }

        // finally, data bind the recycler view with adapter
        recyclerViewMovies.adapter = GridMovieAdapter(movies)

        recyclerViewMovies.addItemDecoration(
            SpacesItemDecoration(resources.getDimensionPixelSize(R.dimen.margin))
        )
    }
    private fun initDataSource() {
        moviesModel = MoviesModel(MoviesDataSourceImpl())
    }

}