package com.example.summer_school_hw.model.data.room.entities

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.example.summer_school_hw.model.data.room.relations.MovieToGenreCrossRef

data class GenreWithMovies (
    @Embedded val genre: Genre,
    @Relation(
        parentColumn = "GenreName",
        entityColumn = "MovieName",
        associateBy = Junction(MovieToGenreCrossRef::class)
    )
    val movies: List<Movie>
)