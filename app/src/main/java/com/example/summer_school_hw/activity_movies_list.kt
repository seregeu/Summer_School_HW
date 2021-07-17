package com.example.summer_school_hw

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.summer_school_hw.data.dto.MovieDto
import com.example.summer_school_hw.data.features.genres.GenresDataSourceImpl
import com.example.summer_school_hw.data.features.movies.MoviesDataSourceImpl
import com.example.summer_school_hw.data.presentation.GenresModel
import com.example.summer_school_hw.data.presentation.MoviesModel

class activity_movies_list : AppCompatActivity(),GridMovieAdapter.OnItemClickListener {
    private lateinit var moviesModel: MoviesModel
    private lateinit var genresModel: GenresModel
    var movies: List<MovieDto> = emptyList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies_list)
        initDataSource()

        val genres = genresModel.getGenres()
        val recyclerViewGenres: RecyclerView = findViewById(R.id.rv_genres)
        recyclerViewGenres.adapter = GenreRecyclerAdapter(genres)

        // initialize a list of Movies
        movies = moviesModel.getMovies()
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
        recyclerViewMovies.adapter = GridMovieAdapter(movies,this)

        recyclerViewMovies.addItemDecoration(
            SpacesItemDecoration(56,30)
        )


    }
    private fun initDataSource() {
        moviesModel = MoviesModel(MoviesDataSourceImpl())
        genresModel = GenresModel(GenresDataSourceImpl())
    }

    override fun onItemClick(position: Int) {

        Toast.makeText(this,movies[position].title+" clicked",Toast.LENGTH_SHORT).show()
    }
}