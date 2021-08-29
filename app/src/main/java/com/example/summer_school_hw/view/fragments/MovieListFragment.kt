package com.example.summer_school_hw.ui.main

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.summer_school_hw.R
import com.example.summer_school_hw.model.data.RecycleAdapters.GenreRecyclerAdapter
import com.example.summer_school_hw.model.data.RecycleAdapters.GridMovieResyclerAdapter
import com.example.summer_school_hw.model.data.RecycleAdapters.SpacesItemDecoration
import com.example.summer_school_hw.model.data.room.entities.Genre
import com.example.summer_school_hw.model.data.room.entities.Movie
import com.example.summer_school_hw.viewmodel.MainViewModel
import com.facebook.shimmer.ShimmerFrameLayout
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*


@AndroidEntryPoint
class MovieListFragment : Fragment(), GridMovieResyclerAdapter.OnItemFilmListener, GenreRecyclerAdapter.OnGenreClickListener {
    private var genres: List<Genre> = emptyList()
    lateinit var recyclerViewMovies: RecyclerView
    private val MovieAdapter: GridMovieResyclerAdapter = GridMovieResyclerAdapter(this)
    private var isListUpdated: Boolean = false
    private val BACK_STACK_ROOT_TAG = "movie_details_fragment"
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private val coroutineHandler =
        CoroutineExceptionHandler { _, exception -> Log.i("Coroutine", "Exception") }

    private val mainViewModel: MainViewModel by activityViewModels()

    private lateinit var navController: NavController

    private lateinit var mShimmerViewContainer: ShimmerFrameLayout
    private val CardMargin: Int
        get() {
            return when (resources.configuration.orientation) {
                Configuration.ORIENTATION_PORTRAIT -> getResources().getDimension(R.dimen.movieCardmarginHorizontalPortrait)
                    .toInt()
                else -> getResources().getDimension(R.dimen.movieCardmarginHorizontalLandscape)
                    .toInt()
            }
        }

    companion object {
        fun newInstance() =
            MovieListFragment().apply {
            }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.main_fragment, container, false)
        viewModelInit()
        mainViewModel.initDatabase(requireContext())
        viewLifecycleOwner.lifecycleScope.launch {
            try {
                mainViewModel.getPopularMoviesList()
            } catch (e: Exception) {
            }
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = view.findNavController()
        mShimmerViewContainer = view.findViewById(R.id.shimmer_view_container)
        mShimmerViewContainer.startShimmer()
        initRecyclerMovies(view)
        initRecyclerViewGenres(view)
        initSwipeRefreshContainer(view)
        restoreConfiguration()

    }

    private fun viewModelInit() {
        mainViewModel.moviesList.observe(viewLifecycleOwner, Observer(::updateList))
        //mainViewModel.getPopularMoviesList().observe(viewLifecycleOwner, Observer(::updateList))
        //mainViewModel.getMovieReleaseData().observe(viewLifecycleOwner, Observer(::getRelease))
    }

    override fun onMovieClick(position: Int) {
        mainViewModel.selectMovie(position)
        navController.navigate(R.id.action_movieListFragment_to_movieDetailsFragment)
    }


    override fun onGenreClick(position: Int) {
        mainViewModel.setMovieGenre(genres[position])
    }

    private fun initSwipeRefreshContainer(view: View) {
        swipeRefreshLayout = view.findViewById(R.id.swipeRefreshLayout)
        swipeRefreshLayout.setOnRefreshListener {
            updateMoviesList()
        }
        swipeRefreshLayout.setColorSchemeResources(
            android.R.color.darker_gray,
            android.R.color.holo_red_light
        );
    }

    //"Uploading movies" using corrutines
    fun updateMoviesList() {
        CoroutineScope(Dispatchers.Main).launch(coroutineHandler) {
            addNewMoviesSuspending()
            swipeRefreshLayout.isRefreshing = false
        }
        isListUpdated = true
    }

    suspend fun addNewMoviesSuspending() = coroutineScope {
    //throw Exception()
        mainViewModel.getPopularMoviesList()
        delay(3000L)
    }

    private fun initRecyclerViewGenres(view: View) {
        val recyclerViewGenres: RecyclerView = view.findViewById(R.id.rv_genres)
        genres = mainViewModel.getListGenres()
        recyclerViewGenres.adapter = GenreRecyclerAdapter(genres, this)
    }

    private fun restoreConfiguration() {
        val moviePosition = mainViewModel.restoreMoviePosition()
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
            SpacesItemDecoration(
                getResources().getDimension(R.dimen.movieCardmarginVervical).toInt(),
                CardMargin
            )
        )
    }

    fun updateList(list: List<Movie>) {
        MovieAdapter.movies = list
        recyclerViewMovies.scrollToPosition(0)
        mShimmerViewContainer.stopShimmer()
        mShimmerViewContainer!!.visibility = View.GONE
        recyclerViewMovies.visibility=View.VISIBLE
    }
}