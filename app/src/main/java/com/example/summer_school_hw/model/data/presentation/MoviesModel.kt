package com.example.summer_school_hw.model.data.presentation

import com.example.summer_school_hw.model.data.dto.features.movies.MoviesDataSource

class MoviesModel(
    private val moviesDataSource: MoviesDataSource
) {

    fun getMovies() = moviesDataSource.getMovies()

    fun downloadMovies()=moviesDataSource.downloadMovies()
}