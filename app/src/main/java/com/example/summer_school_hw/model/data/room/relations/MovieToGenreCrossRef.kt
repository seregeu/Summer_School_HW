package com.example.summer_school_hw.model.data.room.relations

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity()
data class MovieToGenreCrossRef(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long?,
    @ColumnInfo(name = "movie_id")
    val movieId: Long,
    @ColumnInfo(name = "genre_id")
    val GenreId: Long
)