package com.example.summer_school_hw.ui.main

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.summer_school_hw.R
import com.example.summer_school_hw.model.data.RecycleAdapters.GenreRecyclerAdapter
import com.example.summer_school_hw.model.data.RecycleAdapters.GridMovieResyclerAdapter
import com.example.summer_school_hw.model.data.RecycleAdapters.SpacesItemDecoration
import com.example.summer_school_hw.model.data.dto.GenreDto
import com.example.summer_school_hw.model.data.dto.MovieDto
import com.example.summer_school_hw.model.data.features.genres.GenresDataSourceImpl
import com.example.summer_school_hw.model.data.features.movies.MoviesDataSourceImpl
import com.example.summer_school_hw.model.data.presentation.GenresModel
import com.example.summer_school_hw.model.data.presentation.MoviesModel
import com.example.summer_school_hw.viewmodel.MainViewModel
import kotlinx.coroutines.*


class MovieListFragment : Fragment(), GridMovieResyclerAdapter.OnItemFilmListener, GenreRecyclerAdapter.OnGenreClickListener {
    private var genres: List<GenreDto> = emptyList()
    lateinit var recyclerViewMovies: RecyclerView
    private val MovieAdapter: GridMovieResyclerAdapter = GridMovieResyclerAdapter(this)
    private  var isListUpdated: Boolean=false
    private val BACK_STACK_ROOT_TAG = "movie_details_fragment"
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private val coroutineHandler = CoroutineExceptionHandler { _, exception -> Log.i("Coroutine","Exception") }

   private val mainViewModel: MainViewModel by activityViewModels()

    private val CardMargin: Int
        get(){
            return when (resources.configuration.orientation){
                Configuration.ORIENTATION_PORTRAIT->getResources().getDimension(R.dimen.movieCardmarginHorizontalPortrait).toInt()
                else->getResources().getDimension(R.dimen.movieCardmarginHorizontalLandscape).toInt()
            }
        }

    companion object {
        fun newInstance() =
            MovieListFragment().apply{
        }
    }
    
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.main_fragment, container, false)
        viewModelInit()
        initRecyclerMovies(view)
        initRecyclerViewGenres(view)
        initSwipeRefreshContainer(view)
        restoreConfiguration()
        return view
    }

    private fun viewModelInit() {
        mainViewModel.moviesList.observe(viewLifecycleOwner, Observer(::updateList))
    }

    override fun onMovieClick(position: Int){
        mainViewModel.selectMovie(position)
        parentFragmentManager.beginTransaction().replace(
        R.id.fragment_container,
        MovieDetailsFragment.newInstance(MovieAdapter.movies[position])).addToBackStack(BACK_STACK_ROOT_TAG).commit()
    }

    override fun onGenreClick(position: Int) {
        mainViewModel.setMovieGenre(genres[position])
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

    //"Uploading movies" using corrutines
    fun updateMoviesList() {
        CoroutineScope(Dispatchers.Main).launch(coroutineHandler) {
            addNewMoviesSuspending()
            swipeRefreshLayout.isRefreshing = false
        }
        isListUpdated=true
    }

    suspend fun addNewMoviesSuspending() = coroutineScope {
        //throw Exception()
        delay(3000L)
        mainViewModel.downloadMovies()
    }

    private fun initRecyclerViewGenres(view: View) {
        val recyclerViewGenres: RecyclerView = view.findViewById(R.id.rv_genres)
        genres = mainViewModel.getListGenres()
        recyclerViewGenres.adapter = GenreRecyclerAdapter(genres,this)
    }

    private fun restoreConfiguration(){
        val moviePosition=mainViewModel.restoreMovie()
        if (moviePosition!=-1){
            mainViewModel.getMovies()?.get(moviePosition)?.let {
                MovieDetailsFragment.newInstance(
                    it
                )
            }?.let {
                parentFragmentManager.beginTransaction().replace(
                    R.id.fragment_container,
                    it
                ).addToBackStack(BACK_STACK_ROOT_TAG).commit()
            }
        }
    }

    private fun initRecyclerMovies(view: View) {
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
        recyclerViewMovies.addItemDecoration(
            SpacesItemDecoration(getResources().getDimension(R.dimen.movieCardmarginVervical).toInt(),
                CardMargin)
        )
    }

    fun updateList(list: List<MovieDto>) {
        MovieAdapter.movies = list
        recyclerViewMovies.scrollToPosition(0)
    }
}