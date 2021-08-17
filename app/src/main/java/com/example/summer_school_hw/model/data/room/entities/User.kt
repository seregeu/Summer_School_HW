package com.example.summer_school_hw.model.data.room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Users")
data class User(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "user_id")
    val id: Long,
    @ColumnInfo(name = "user_name")
    val name: String,
    @ColumnInfo(name = "user_passwd")
    val passwd: String, val email: String
)
