package com.example.summer_school_hw.data.presentation

import com.example.summer_school_hw.data.dto.features.movies.MoviesDataSource

class MoviesModel(
    private val moviesDataSource: MoviesDataSource
) {

    fun getMovies() = moviesDataSource.getMovies()
}