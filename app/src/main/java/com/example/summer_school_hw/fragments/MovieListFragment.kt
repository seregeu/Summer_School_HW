package com.example.summer_school_hw.ui.main

import android.content.res.Configuration
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
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
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import java.lang.Exception


class MovieListFragment : Fragment(), GridMovieResyclerAdapter.OnItemFilmListener, GenreRecyclerAdapter.OnGenreClickListener {
    private lateinit var moviesModel: MoviesModel
    private lateinit var genresModel: GenresModel
    var movies: List<MovieDto> = emptyList()
    var genres: List<GenreDto> = emptyList()
    lateinit var recyclerViewMovies: RecyclerView
    val MovieAdapter: GridMovieResyclerAdapter = GridMovieResyclerAdapter(this)
    private val BACK_STACK_ROOT_TAG = "root_fragment"
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    val coroutine_handler = CoroutineExceptionHandler { _, exception -> Log.i("Coroutine","Exception") }

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
        initRecyclerViewGenres(view)
        initRecyclerMovies(view)
        initSwipeRefreshContainer(view)

        return view
    }
    override fun onMovieClick(position: Int){
        parentFragmentManager.beginTransaction().replace(
        R.id.fragment_container,
        MovieDetailsFragment.newInstance(MovieAdapter.movies[position])).addToBackStack(BACK_STACK_ROOT_TAG).commit()
    }
    override fun onGenreClick(position: Int) {
        try {
            val oldList = movies
            val new_list = oldList.filter { it.genre.contains(genres[position]) }
            updateList(new_list)
        } catch (e: NumberFormatException) {
        }
    }

    private fun initSwipeRefreshContainer(view: View){
        swipeRefreshLayout = view.findViewById(R.id.swipeRefreshLayout)

        swipeRefreshLayout.setOnRefreshListener {
           updateMoviesList()
        }
        swipeRefreshLayout.setColorSchemeResources(
            android.R.color.darker_gray,
            android.R.color.holo_red_light);
    }

    fun updateMoviesList() {
        CoroutineScope(Dispatchers.Main).launch(coroutine_handler) {
            movies = addNewMoviesSuspending()+moviesModel.getMovies()
            swipeRefreshLayout.isRefreshing = false
            MainScope().launch {
                updateList(movies)
            }
        }
    }

    suspend fun addNewMoviesSuspending() = coroutineScope {
        var newList: List<MovieDto> = emptyList()
        delay(3000L)
        newList = moviesModel.downloadMovies()
        return@coroutineScope newList
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
        recyclerViewMovies.scrollToPosition(0)
    }

}