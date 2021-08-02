package com.example.summer_school_hw.model.data.features.genres

import com.example.summer_school_hw.model.data.dto.GenreDto

interface GenresDataSouce {
    fun getGenres(): List<GenreDto>
}