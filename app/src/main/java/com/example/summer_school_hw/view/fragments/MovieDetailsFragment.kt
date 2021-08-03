package com.example.summer_school_hw.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.summer_school_hw.R
import com.example.summer_school_hw.model.data.RecycleAdapters.ActorRecyclerAdapter
import com.example.summer_school_hw.model.data.dto.ActorDto
import com.example.summer_school_hw.model.data.dto.GenreDto
import com.example.summer_school_hw.model.data.dto.MovieDto
import com.example.summer_school_hw.model.data.presentation.ActorsModel


class MovieDetailsFragment : Fragment(){
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

    private lateinit var moviePoster: ImageView
    private lateinit var movieNameTextView: TextView
    private lateinit var movieDescriptionTextView: TextView
    private lateinit var movieAgeTextView: TextView
    private lateinit var movieRatingBar: RatingBar
    private lateinit var movieGenreTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            movieName = it.getString("movieTitle")
            movieGenreName = it.getString("movieGenreName")
            movieStarNumber = it.getInt("movieStarNumber")
            movieAge = it.getInt("movieAgeLimit")
            movieDescription = it.getString("movieDescription")
            movieImageUrl = it.getString("movieImageUrl")
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
        initRecyclerActors(view)
        PutDataToForm(view)
    }

    private fun PutDataToForm(view: View){
        moviePoster = view.findViewById(R.id.shapeableImageView)
        movieNameTextView = view.findViewById(R.id.text_film_name)
        movieDescriptionTextView = view.findViewById(R.id.text_film_description)
        movieAgeTextView = view.findViewById(R.id.text_age_limit)
        movieRatingBar = view.findViewById(R.id.ratingBar_indicator)
        movieGenreTextView = view.findViewById(R.id.text_view_genre)

        moviePoster.load(movieImageUrl)
        movieNameTextView.text = movieName
        movieDescriptionTextView.text = movieDescription
        movieAgeTextView.text = movieAge.toString() + "+"
        movieGenreTextView.text = movieGenreName
        movieRatingBar.rating = movieStarNumber!!.toFloat()
    }

    companion object {
        @JvmStatic
        fun newInstance(movie: MovieDto) =
            MovieDetailsFragment().apply {
                arguments = Bundle().apply {
                    putString("movieTitle", movie.title)
                    putString("movieDescription", movie.description)
                    putInt("movieStarNumber", movie.rateScore)
                    putInt("movieAgeLimit", movie.ageRestriction)
                    putString("movieGenreName", movie.genre[0].genreName)
                    putString("movieImageUrl", movie.posterUrl)
                    actors=movie.actors
                }
            }
    }

    private fun initRecyclerActors(view: View) {
        val recyclerViewActors: RecyclerView = view.findViewById(R.id.rv_actors)
        recyclerViewActors.adapter = ActorRecyclerAdapter(actors)
    }
}