package com.example.summer_school_hw.data.presentation

import com.example.summer_school_hw.data.features.actors.ActorsDataSource

class ActorsModel(
    private val moviesDataSource: ActorsDataSource
) {

    fun getActors() = moviesDataSource.getActors()
}