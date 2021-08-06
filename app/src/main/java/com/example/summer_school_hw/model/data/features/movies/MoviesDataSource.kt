package com.example.summer_school_hw.model.data.dto.features.movies

import com.example.summer_school_hw.model.data.dto.MovieDto

interface MoviesDataSource {
	fun getMovies(): List<MovieDto>
	fun downloadMovies(): List<MovieDto>
}