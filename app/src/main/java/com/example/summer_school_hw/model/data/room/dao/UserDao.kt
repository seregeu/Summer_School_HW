package com.example.summer_school_hw.model.data.room.dao

import androidx.room.*
import com.example.summer_school_hw.model.data.room.entities.User

@Dao
interface UserDao {
    @Query("SELECT * FROM users")
    fun getAll(): List<User>

    @Insert
    fun insertAll(users: List<User>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: User): Long

    @Update
    fun update(user: User)

    @Delete
    fun delete(user: User)

    @Query("DELETE FROM users WHERE user_id = :userId")
    fun deleteById(userId: Long)

    @Query("DELETE FROM users")
    fun deleteAll()
}