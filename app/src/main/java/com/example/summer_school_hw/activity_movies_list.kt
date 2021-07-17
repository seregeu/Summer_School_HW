package com.example.summer_school_hw

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.summer_school_hw.data.dto.GenreDto
import com.example.summer_school_hw.data.dto.MovieDto
import com.example.summer_school_hw.data.features.genres.GenresDataSourceImpl
import com.example.summer_school_hw.data.features.movies.MoviesDataSourceImpl
import com.example.summer_school_hw.data.presentation.GenresModel
import com.example.summer_school_hw.data.presentation.MoviesModel

class activity_movies_list : AppCompatActivity(),GridMovieAdapter.OnItemFilmListener, GenreRecyclerAdapter.OnGenreClickListener {
    private lateinit var moviesModel: MoviesModel
    private lateinit var genresModel: GenresModel
    var movies: List<MovieDto> = emptyList()
    var genres: List<GenreDto> = emptyList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies_list)
        initDataSource()

        genres = genresModel.getGenres()
        val recyclerViewGenres: RecyclerView = findViewById(R.id.rv_genres)
        recyclerViewGenres.adapter = GenreRecyclerAdapter(genres,this)

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
            SpacesItemDecoration(getResources().getDimension(R.dimen.movieCardmarginVervical).toInt(),
                getResources().getDimension(R.dimen.movieCardmarginHorizontal).toInt())
        )
    }
    private fun initDataSource() {
        moviesModel = MoviesModel(MoviesDataSourceImpl())
        genresModel = GenresModel(GenresDataSourceImpl())
    }

    override fun onItemClick(position: Int, mode: Int) {
        when (mode) {
            1 -> Toast.makeText(this, movies[position].title + " clicked", Toast.LENGTH_SHORT).show()
            2 -> {Toast.makeText(this, genres[position].genreName + " clicked", Toast.LENGTH_SHORT).show()
                try {
                    val old_list = moviesModel.getMovies()
                    val new_list = old_list.takeWhile { it.rateScore==genres[position].genreName.toInt() }
                } catch (e: NumberFormatException) {
                    Toast.makeText(this, "Not implemented", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

}