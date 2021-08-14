package com.example.summer_school_hw.model.data.room.relations

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(primaryKeys = ["title", "genre_name"])
data class MovieToGenreCrossRef(
    @ColumnInfo(name = "title")
    val movieName: String,
    @ColumnInfo(name = "genre_name")
    val GenreName: String
)