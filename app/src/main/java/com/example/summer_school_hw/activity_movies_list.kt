package com.example.summer_school_hw

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.summer_school_hw.data.RecycleAdapters.GenreRecyclerAdapter
import com.example.summer_school_hw.data.RecycleAdapters.GridMovieResyclerAdapter
import com.example.summer_school_hw.data.RecycleAdapters.SpacesItemDecoration
import com.example.summer_school_hw.data.dto.GenreDto
import com.example.summer_school_hw.data.dto.MovieDto
import com.example.summer_school_hw.data.features.genres.GenresDataSourceImpl
import com.example.summer_school_hw.data.features.movies.MoviesDataSourceImpl
import com.example.summer_school_hw.data.presentation.GenresModel
import com.example.summer_school_hw.data.presentation.MoviesModel

class activity_movies_list : AppCompatActivity(), GridMovieResyclerAdapter.OnItemFilmListener, GenreRecyclerAdapter.OnGenreClickListener {
    private lateinit var moviesModel: MoviesModel
    private lateinit var genresModel: GenresModel
    var movies: List<MovieDto> = emptyList()
    var genres: List<GenreDto> = emptyList()
    lateinit var recyclerViewMovies: RecyclerView
    val the_adapter: GridMovieResyclerAdapter = GridMovieResyclerAdapter(this)

    private val CardMargin: Int
    get(){
        return when (resources.configuration.orientation){
            Configuration.ORIENTATION_PORTRAIT->getResources().getDimension(R.dimen.movieCardmarginHorizontalPortrait).toInt()
            else->getResources().getDimension(R.dimen.movieCardmarginHorizontalLandscape).toInt()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies_list)
        initDataSource()
        val recyclerViewGenres: RecyclerView = findViewById(R.id.rv_genres)
        genres = genresModel.getGenres()
        recyclerViewGenres.adapter = GenreRecyclerAdapter(genres,this)
        // initialize a list of Movies
        movies = moviesModel.getMovies()
        recyclerViewMovies = findViewById(R.id.rv_movies_list)
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
        recyclerViewMovies.adapter = the_adapter
        updateList(movies)
        recyclerViewMovies.addItemDecoration(
            SpacesItemDecoration(getResources().getDimension(R.dimen.movieCardmarginVervical).toInt(),
                CardMargin)
        )
    }
    private fun initDataSource() {
        moviesModel = MoviesModel(MoviesDataSourceImpl())
        genresModel = GenresModel(GenresDataSourceImpl())
    }

    override fun onItemClick(position: Int, mode: Int) {
        when (mode) {
            1 -> Toast.makeText(this, the_adapter.movies[position].title + " clicked", Toast.LENGTH_SHORT).show()
            2 -> {Toast.makeText(this, genres[position].genreName + " clicked", Toast.LENGTH_SHORT).show()
                try {
                    val oldList = moviesModel.getMovies()
                    val newList = oldList.filter { it.genre.contains(genres[position])}
                    updateList(newList)
                } catch (e: NumberFormatException) {
                    Toast.makeText(this, "There is no films in such genre :((", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    fun updateList(list: List<MovieDto>) {
        the_adapter.movies = list
    }

}