package com.example.summer_school_hw.model.data.room.relations

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.example.summer_school_hw.model.data.room.entities.Genre
import com.example.summer_school_hw.model.data.room.entities.Movie

data class GenreWithMovies (
    @Embedded val genre: Genre,
    @Relation(
        parentColumn = "genre_id",
        entityColumn = "movie_id",
        associateBy = Junction(MovieToGenreCrossRef::class)
    )
    val movies: List<Movie>
)