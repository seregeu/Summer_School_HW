package com.example.summer_school_hw.model.data.room.relations

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(primaryKeys = ["title", "name"])
data class MovieToActorCrossRef(
    @ColumnInfo(name = "title")
    val movieName: String,
    @ColumnInfo(name = "name")
    val ActorName: String
)