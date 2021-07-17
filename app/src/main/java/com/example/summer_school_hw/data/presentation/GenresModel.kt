package com.example.summer_school_hw.data.presentation

import com.example.summer_school_hw.data.features.genres.GenresDataSouce

class GenresModel(
    private val genresDataSource: GenresDataSouce
) {

    fun getGenres() = genresDataSource.getGenres()
}