package com.example.summer_school_hw.model.data.presentation

import com.example.summer_school_hw.model.data.features.genres.GenresDataSouce

class GenresModel(
    private val genresDataSource: GenresDataSouce
) {

    fun getGenres() = genresDataSource.getGenres()
}