package com.example.summer_school_hw.model.data.room.relations

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.example.summer_school_hw.model.data.room.entities.Genre
import com.example.summer_school_hw.model.data.room.entities.Movie

data class MovieWithGenres (
    @Embedded val movie: Movie,
    @Relation(
        parentColumn = "movie_id",
        entityColumn = "genre_id",
        associateBy = Junction(MovieToGenreCrossRef::class)
    )
    val genres: List<Genre>
)