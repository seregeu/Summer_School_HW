package com.example.summer_school_hw.model.data.room.relations

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity()
data class MovieToGenreCrossRef(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long?,
    @ColumnInfo(name = "title")
    val movieName: String,
    @ColumnInfo(name = "genre_name")
    val GenreName: String
)