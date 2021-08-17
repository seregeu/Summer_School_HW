package com.example.summer_school_hw.model.data.room.dao

import androidx.room.*
import com.example.summer_school_hw.model.data.room.entities.Actor

@Dao
interface ActorDao {
    @Query("SELECT * FROM actors")
    fun getAll(): List<Actor>

    @Insert
    fun insertAll(actors: List<Actor>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(actor: Actor): Long

    @Update
    fun update(actor: Actor)

    @Delete
    fun delete(actor: Actor)

    @Query("DELETE FROM actors WHERE actor_id = :actorId")
    fun deleteById(actorId: Long)

    @Query("DELETE FROM actors")
    fun deleteAll()
}