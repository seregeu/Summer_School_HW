package com.example.summer_school_hw.data.features.genres

import com.example.summer_school_hw.R
import com.example.summer_school_hw.data.dto.ActorDto
import com.example.summer_school_hw.data.dto.GenreDto

class GenresDataSourceImpl:GenresDataSouce {
    override fun getGenres()=listOf(
        GenreDto("18", 0),
        GenreDto("Боевик", 28),
        GenreDto("Приключения", 12),
        GenreDto("Мультик", 16),
        GenreDto("Комедия", 35),
        GenreDto("Криминал", 80),
        GenreDto("Документальное", 99),
        GenreDto("Семейный", 10751),
        GenreDto("Фантастика", 14),
        GenreDto("Исторический", 36),
        GenreDto("Музыка", 10402),
        GenreDto("Мистика", 9648),
        GenreDto("Романтика", 10749),
        GenreDto("Научная фантастика", 879),
        GenreDto("Триллер", 53),
        GenreDto("Военный", 10752),
        GenreDto("Вестерн", 37  ),
        GenreDto("Рельное ТВ", 10770),
    )
}