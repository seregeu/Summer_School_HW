package com.example.summer_school_hw.ui.main

import android.os.Bundle
import android.provider.Settings.Global.putInt
import android.provider.Settings.Global.putString
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
import com.example.summer_school_hw.model.data.dto.ActorDto
import com.example.summer_school_hw.model.data.dto.GenreDto
import com.example.summer_school_hw.model.data.dto.MovieDto
import com.example.summer_school_hw.model.data.presentation.ActorsModel
import com.example.summer_school_hw.viewmodel.MainViewModel


class MovieDetailsFragment : Fragment() {
    var movies: List<MovieDto> = emptyList()
    var genres: List<GenreDto> = emptyList()
    var actors: List<ActorDto> = emptyList()
    lateinit var recyclerViewActors: RecyclerView

    private var movieName: String? = null
    private var movieDescription: String? = null
    private var movieStarNumber: Int? = null
    private var movieAge: Int? = null
    private var movieImageUrl: String? = null
    private var movieGenreName: String? = null
    private var movieItem: Int? = null

    private lateinit var moviePoster: ImageView
    private lateinit var movieNameTextView: TextView
    private lateinit var movieDescriptionTextView: TextView
    private lateinit var movieAgeTextView: TextView
    private lateinit var movieRatingBar: RatingBar
    private lateinit var movieGenreTextView: TextView

    private val mainViewModel: MainViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            /* movieName = it.getString("movieTitle")
            movieGenreName = it.getString("movieGenreName")
            movieStarNumber = it.getInt("movieStarNumber")
            movieAge = it.getInt("movieAgeLimit")
            movieDescription = it.getString("movieDescription")
            movieImageUrl = it.getString("movieImageUrl")*/
            movieItem = it.getInt("MOVIE")
        }
    }

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
        }
        initRecyclerActors(view)
    }

    private fun PutDataToForm(view: View, movie:MovieDto) {
        moviePoster = view.findViewById(R.id.shapeableImageView)
        movieNameTextView = view.findViewById(R.id.text_film_name)
        movieDescriptionTextView = view.findViewById(R.id.text_film_description)
        movieAgeTextView = view.findViewById(R.id.text_age_limit)
        movieRatingBar = view.findViewById(R.id.ratingBar_indicator)
        movieGenreTextView = view.findViewById(R.id.text_view_genre)

        moviePoster.load(movie.posterUrl)
        movieNameTextView.text = movie.title
        movieDescriptionTextView.text = movie.description
        movieAgeTextView.text = movie.ageRestriction.toString() + "+"
        movieGenreTextView.text = movie.genre[0].genreName
        movieRatingBar.rating = movie.rateScore!!.toFloat()
        actors=movie.actors
    }

    private fun initRecyclerActors(view: View) {
        val recyclerViewActors: RecyclerView = view.findViewById(R.id.rv_actors)
        recyclerViewActors.adapter = ActorRecyclerAdapter(actors)
    }
}