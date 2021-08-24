package com.example.summer_school_hw.model.data.room.dao

import androidx.room.*
import com.example.summer_school_hw.model.data.room.entities.Actor
import com.example.summer_school_hw.model.data.room.entities.Genre

@Dao
interface GenreDao {
    @Query("SELECT * FROM genres")
    fun getAll(): List<Genre>

    @Insert
    fun insertAll(genres: List<Genre>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(genre: Genre): Long

    @Update
    fun update(genre: Genre)

    @Delete
    fun delete(genre: Genre)

    @Query("DELETE FROM genres WHERE genre_id = :genreId")
    fun deleteById(genreId: Long)

    @Query("DELETE FROM genres WHERE genre_name = :genreName")
    fun deleteByName(genreName: String)

    @Transaction
    @Query("SELECT * FROM genres WHERE genre_name = :genreName")
    fun getGenreByName(genreName: String): Genre

    @Query("DELETE FROM genres")
    fun deleteAll()
}