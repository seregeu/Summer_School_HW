package com.example.summer_school_hw.data.features.genres

import com.example.summer_school_hw.data.dto.GenreDto

interface GenresDataSouce {
    fun getGenres(): List<GenreDto>
}