package com.example.summer_school_hw.ui.main

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.summer_school_hw.R
import com.example.summer_school_hw.data.RecycleAdapters.GenreRecyclerAdapter
import com.example.summer_school_hw.data.RecycleAdapters.GridMovieResyclerAdapter
import com.example.summer_school_hw.data.RecycleAdapters.SpacesItemDecoration
import com.example.summer_school_hw.data.dto.GenreDto
import com.example.summer_school_hw.data.dto.MovieDto
import com.example.summer_school_hw.data.features.genres.GenresDataSourceImpl
import com.example.summer_school_hw.data.features.movies.MoviesDataSourceImpl
import com.example.summer_school_hw.data.presentation.GenresModel
import com.example.summer_school_hw.data.presentation.MoviesModel


class MovieListFragment : Fragment(), GridMovieResyclerAdapter.OnItemFilmListener, GenreRecyclerAdapter.OnGenreClickListener {
    private lateinit var moviesModel: MoviesModel
    private lateinit var genresModel: GenresModel
    var movies: List<MovieDto> = emptyList()
    var genres: List<GenreDto> = emptyList()
    lateinit var recyclerViewMovies: RecyclerView
    val MovieAdapter: GridMovieResyclerAdapter = GridMovieResyclerAdapter(this)
    private val BACK_STACK_ROOT_TAG = "root_fragment"
    private val CardMargin: Int
        get(){
            return when (resources.configuration.orientation){
                Configuration.ORIENTATION_PORTRAIT->getResources().getDimension(R.dimen.movieCardmarginHorizontalPortrait).toInt()
                else->getResources().getDimension(R.dimen.movieCardmarginHorizontalLandscape).toInt()
            }
        }

    companion object {
        fun newInstance() = MovieListFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.main_fragment, container, false)
        initDataSource()
        initRecyclerViewGenres(view);
        initRecyclerMovies(view);
        return view
    }

    override fun onItemClick(position: Int, mode: Int) {
        when (mode) {
            1 -> {
                parentFragmentManager.beginTransaction().replace(
                    R.id.fragment_container,
                    MovieDetailsFragment.newInstance(MovieAdapter.movies[position])).addToBackStack(BACK_STACK_ROOT_TAG).commit()
            }
            2 -> {
                //Toast.makeText(this, genres[position].genreName + " clicked", Toast.LENGTH_SHORT).show()
                try {
                    val oldList = moviesModel.getMovies()
                    val newList = oldList.filter { it.genre.contains(genres[position])}
                    updateList(newList)
                } catch (e: NumberFormatException) {
                    // Toast.makeText(this, "There is no films in such genre :((", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }


    private fun initRecyclerViewGenres(view: View) {
        val recyclerViewGenres: RecyclerView = view.findViewById(R.id.rv_genres)
        genres = genresModel.getGenres()
        recyclerViewGenres.adapter = GenreRecyclerAdapter(genres,this)
    }

    private fun initRecyclerMovies(view: View) {
        movies = moviesModel.getMovies()
        recyclerViewMovies = view.findViewById(R.id.rv_movies_list)
        // initialize grid layout manager
        GridLayoutManager(
            context, // context
            2, // span count
            RecyclerView.VERTICAL, // orientation
            false // reverse layout
        ).apply {
            recyclerViewMovies.layoutManager = this
        }
        recyclerViewMovies.adapter = MovieAdapter
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

    fun updateList(list: List<MovieDto>) {
        MovieAdapter.movies = list
    }

}