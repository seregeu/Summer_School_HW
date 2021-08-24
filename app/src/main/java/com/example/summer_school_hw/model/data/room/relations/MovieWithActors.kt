package com.example.summer_school_hw.model.data.room.relations

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.example.summer_school_hw.model.data.room.entities.Actor
import com.example.summer_school_hw.model.data.room.entities.Movie

data class MovieWithActors (
    @Embedded val movie: Movie,
    @Relation(
        parentColumn = "movie_id",
        entityColumn = "actor_id",
        associateBy = Junction(MovieToActorCrossRef::class)
    )
    val actors: List<Actor>
)