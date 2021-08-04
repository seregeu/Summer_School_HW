package com.example.summer_school_hw.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.summer_school_hw.model.data.dto.GenreDto
import com.example.summer_school_hw.model.data.dto.MovieDto
import com.example.summer_school_hw.model.data.features.genres.GenresDataSourceImpl
import com.example.summer_school_hw.model.data.features.movies.MoviesDataSourceImpl
import com.example.summer_school_hw.model.data.presentation.GenresModel
import com.example.summer_school_hw.model.data.presentation.MoviesModel
import com.example.summer_school_hw.view.MainActivity


class  MainViewModel: ViewModel() {
    //models
    private var moviesModel = MoviesModel(MoviesDataSourceImpl())
    private var genresModel = GenresModel(GenresDataSourceImpl())

    //data lists
    val moviesList: LiveData<List<MovieDto>> get() = _moviesList
    private val _moviesList = MutableLiveData<List<MovieDto>>()

    private var moviePosition: Int = -1

    init {
        loadMovies()
    }

    fun loadMovies(){
        _moviesList.postValue(moviesModel.getMovies())
    }

    fun setMovieGenre(genre: GenreDto){
        try {
            _moviesList.postValue(moviesModel.getMovies().filter { it.genre.contains(genre) })
        } catch (e: NumberFormatException) {
        }
    }

    fun downloadMovies(){
        _moviesList.postValue(moviesModel.downloadMovies() + moviesModel.getMovies())
    }

    fun getListGenres()= genresModel.getGenres()

    fun getMovies() = _moviesList.value

    fun selectMovie(_moviePosition:Int){
        moviePosition=_moviePosition
    }

    fun restoreMovie()=moviePosition
}