package com.example.summer_school_hw.model.data.room.entities

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.example.summer_school_hw.model.data.room.relations.MovieToActorCrossRef

data class ActorWithMovies (
    @Embedded val actor: Actor,
    @Relation(
        parentColumn = "ActorID",
        entityColumn = "MovieID",
        associateBy = Junction(MovieToActorCrossRef::class)
    )
    val movies: List<Movie>
)