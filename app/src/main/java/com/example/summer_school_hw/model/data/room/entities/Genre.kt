package com.example.summer_school_hw.model.data.room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Genres")
data class Genre (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "genre_id")
    val id: Long? = null,

    @ColumnInfo(name = "genre_name")
    val genreName: String,

    @ColumnInfo(name = "rest_id")
    val restId: Int)