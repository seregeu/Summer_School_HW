package com.example.summer_school_hw.ui.main

import android.os.Bundle
import androidx.lifecycle.Observer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.summer_school_hw.R
import com.example.summer_school_hw.model.data.RecycleAdapters.ActorRecyclerAdapter
import com.example.summer_school_hw.model.data.room.entities.Actor
import com.example.summer_school_hw.model.data.room.entities.Genre
import com.example.summer_school_hw.model.data.room.entities.Movie
import com.example.summer_school_hw.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailsFragment : Fragment() {
    var _actors: List<Actor> = emptyList()
    lateinit var recyclerViewActors: RecyclerView
    lateinit var genre: Genre

    private var movieItem: Int? = null

    private lateinit var moviePoster: ImageView
    private lateinit var movieNameTextView: TextView
    private lateinit var movieDescriptionTextView: TextView
    private lateinit var movieAgeTextView: TextView
    private lateinit var movieRatingBar: RatingBar
    private lateinit var movieGenreTextView: TextView
    private lateinit var movieReleaseDate: TextView
    private val actorsAdapter: ActorRecyclerAdapter = ActorRecyclerAdapter()

    private val mainViewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val movieDetailView = inflater.inflate(R.layout.fragment_movie_details, container, false)
        return movieDetailView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val movie = mainViewModel.restoreMovie()
        if (movie != null) {
            PutDataToForm(view,movie)
            initObservers(movie.idMDB,view)
        }
        initRecyclerActors(view)
    }

    fun initObservers(movieId: Int, view: View){
        mainViewModel.getMovieCreditsById(movieId).observe(viewLifecycleOwner, Observer(::getActors))
    }

    fun getActors(actorsList: List<Actor>){
        _actors = actorsList
        actorsAdapter.actors=_actors
    }

    private fun PutDataToForm(view: View, movie: Movie) {
        moviePoster = view.findViewById(R.id.shapeableImageView)
        movieNameTextView = view.findViewById(R.id.text_film_name)
        movieDescriptionTextView = view.findViewById(R.id.text_film_description)
        movieAgeTextView = view.findViewById(R.id.text_age_limit)
        movieRatingBar = view.findViewById(R.id.ratingBar_indicator)
        movieGenreTextView = view.findViewById(R.id.text_view_genre)
        movieReleaseDate = view.findViewById(R.id.text_view_data)

        moviePoster.load(movie.posterUrl)
        movieNameTextView.text = movie.title
        movieDescriptionTextView.text = movie.description
        movieReleaseDate.text = movie.releaseDate
        movieAgeTextView.text = movie.ageRestriction.toString() + "+"
        val genre = mainViewModel.restoreGenre(movie)
        if (genre != null) {
            movieGenreTextView.text = genre.genreName
        }
        movieRatingBar.rating = movie.rateScore.toFloat()
    }

    private fun initRecyclerActors(view: View) {
        val recyclerViewActors: RecyclerView = view.findViewById(R.id.rv_actors)
        recyclerViewActors.adapter = actorsAdapter
    }
}