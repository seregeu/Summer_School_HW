package com.example.summer_school_hw.ui.main

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
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
    private lateinit var moviesModel: MoviesModel
    private lateinit var genresModel: GenresModel
    private var movies: List<MovieDto> = emptyList()
    private var genres: List<GenreDto> = emptyList()
    lateinit var recyclerViewMovies: RecyclerView
    lateinit private var movieListListener:OnMovieLisChangeListener
    lateinit private var movieUpdateListener:OnMovieListUpdateListener
    lateinit private var movieTapedListener:OnMovieitemTapedListener
    private val MovieAdapter: GridMovieResyclerAdapter = GridMovieResyclerAdapter(this)
    private var initGenre:Int = -1
    private var moviePosition:Int = -1
    private  var isListUpdated: Boolean=false
    private val BACK_STACK_ROOT_TAG = "movie_details_fragment"
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private val coroutineHandler = CoroutineExceptionHandler { _, exception -> Log.i("Coroutine","Exception") }

    private val mainViewModel by lazy {ViewModelProviders.of(this).get(MainViewModel::class.java)}


    private val CardMargin: Int
        get(){
            return when (resources.configuration.orientation){
                Configuration.ORIENTATION_PORTRAIT->getResources().getDimension(R.dimen.movieCardmarginHorizontalPortrait).toInt()
                else->getResources().getDimension(R.dimen.movieCardmarginHorizontalLandscape).toInt()
            }
        }

    companion object {
        fun newInstance(genreChangeListener:OnMovieLisChangeListener,
                        movieListUpdatedListener: OnMovieListUpdateListener,
                        _movieTapedListener:OnMovieitemTapedListener,
                        _initGenre:Int, _isListUpdated:Boolean,_moviePosition: Int) =
            MovieListFragment().apply{
                movieListListener = genreChangeListener
                movieUpdateListener = movieListUpdatedListener
                movieTapedListener = _movieTapedListener
                initGenre = _initGenre
                isListUpdated = _isListUpdated
                moviePosition = _moviePosition
        }
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
        //restoreConfiguration()
        viewModelInit()
        return view
    }

    private fun viewModelInit() {
        mainViewModel.moviesList.observe(viewLifecycleOwner, Observer(::updateList))
        mainViewModel.getMovies()?.let { updateList(it) }
    }

    override fun onMovieClick(position: Int){
        movieTapedListener.OnMovieSelected(position)
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

    private fun restoreConfiguration(){
        //full movie list recovery
        if (isListUpdated==true){
            movies=moviesModel.downloadMovies()+movies
        }
        var _movies = movies
        if(initGenre!=-1){
            try {
                _movies = movies.filter{ it.genre.contains(genres[initGenre]) }
            } catch (e: NumberFormatException) {
            }
        }
        updateList(_movies)
        //movieDetails fragment recovery
        if (moviePosition!=-1){
            parentFragmentManager.beginTransaction().replace(
                R.id.fragment_container,
                MovieDetailsFragment.newInstance(MovieAdapter.movies[moviePosition])).addToBackStack(BACK_STACK_ROOT_TAG).commit()
            moviePosition=-1
            movieTapedListener.OnMovieSelected(moviePosition)
        }
    }

    //"Uploading movies" using corrutines
    fun updateMoviesList() {
        CoroutineScope(Dispatchers.Main).launch(coroutineHandler) {
            addNewMoviesSuspending()
            swipeRefreshLayout.isRefreshing = false
            movieListListener.onMovieListChanged(-1)
        }
        movieUpdateListener.OnMovieListUpdated()
        isListUpdated=true
    }

    suspend fun addNewMoviesSuspending() = coroutineScope {
        //throw Exception()
        delay(3000L)
        mainViewModel.downloadMovies()
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
        //updateList(movies)
        recyclerViewMovies.addItemDecoration(
            SpacesItemDecoration(getResources().getDimension(R.dimen.movieCardmarginVervical).toInt(),
                CardMargin)
        )
    }

    interface OnMovieLisChangeListener{
        fun onMovieListChanged(genrePosition: Int)
    }

    interface OnMovieListUpdateListener{
        fun OnMovieListUpdated()
    }

    interface OnMovieitemTapedListener{
        fun OnMovieSelected(moviePosition: Int)
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