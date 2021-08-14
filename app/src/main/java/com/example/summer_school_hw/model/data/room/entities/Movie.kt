package com.example.summer_school_hw.model.data.room.entities

import androidx.room.*
import com.example.summer_school_hw.model.data.dto.ActorDto
import com.example.summer_school_hw.model.data.dto.GenreDto

@Entity(tableName = "Movies")
data class Movie (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long? = null,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "description")
    val description: String,

    @ColumnInfo(name = "rate_score")
    val rateScore: Int,

    @ColumnInfo(name = "age_restriction")
    val ageRestriction: Int,

    @ColumnInfo(name = "image_url")
    val imageUrl: String,

    @ColumnInfo(name = "poster_url")
    val posterUrl: String,
)