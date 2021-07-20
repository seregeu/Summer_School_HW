package com.example.summer_school_hw.data.dto.features.movies

import com.example.summer_school_hw.data.dto.MovieDto

interface MoviesDataSource {
	fun getMovies(): List<MovieDto>
}