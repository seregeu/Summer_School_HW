package com.example.summer_school_hw.model.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.summer_school_hw.model.data.room.dao.ActorDao
import com.example.summer_school_hw.model.data.room.dao.GenreDao
import com.example.summer_school_hw.model.data.room.dao.MovieDao
import com.example.summer_school_hw.model.data.room.dao.UserDao
import com.example.summer_school_hw.model.data.room.entities.*
import com.example.summer_school_hw.model.data.room.relations.MovieToActorCrossRef
import com.example.summer_school_hw.model.data.room.relations.MovieToGenreCrossRef
import com.example.summer_school_hw.model.data.room.relations.UserToGenreCrossRef

@Database(entities = [
    Movie::class,
    Actor::class,
    Genre::class,
    MovieToActorCrossRef::class,
    MovieToGenreCrossRef::class,
    User::class,
    UserToGenreCrossRef::class], version = 18)


abstract class ApplicationDatabase: RoomDatabase() {

    abstract fun movieDao(): MovieDao
    abstract fun actorDao(): ActorDao
    abstract fun genreDao(): GenreDao
    abstract fun userDao(): UserDao

    companion object {
        private const val DATABASE_NAME = "movies_db"
        @Volatile
        private var instance: ApplicationDatabase? = null

        fun initDatabase(context: Context) {
            if (instance == null) {
                synchronized(ApplicationDatabase::class) {
                    instance = Room.databaseBuilder(context.applicationContext,
                        ApplicationDatabase::class.java, DATABASE_NAME).allowMainThreadQueries()
                        .fallbackToDestructiveMigration().build().also {
                            instance=it
                        }
                }
            }
        }

        fun getInstance(): ApplicationDatabase? {
            return instance
        }

        fun destroyInstance() {
            instance = null
        }
    }
}