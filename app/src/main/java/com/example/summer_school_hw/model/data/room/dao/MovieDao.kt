package com.example.summer_school_hw.model.data.room.dao
import androidx.room.*
import com.example.summer_school_hw.model.data.room.entities.Movie
import com.example.summer_school_hw.model.data.room.relations.*


@Dao
interface MovieDao {
    @Query("SELECT * FROM movies")
    fun getAll(): List<Movie>

    @Transaction
    @Query("SELECT * FROM movies WHERE title = :movieName")
    fun getGenreOfMovie(movieName: String): List<MovieWithGenres>

    @Transaction
    @Query("SELECT * FROM genres WHERE genre_name = :genreName")
    fun getMovieOfGenre(genreName: String): List<GenreWithMovies>

    @Transaction
    @Query("SELECT * FROM movies WHERE title = :movieName")
    fun getActorsOfMovie(movieName: String): List<MovieWithActors>

  /*  @Transaction
    @Query("SELECT * FROM movies WHERE  title = :movieName")
    suspend fun getActorOfMovie(movieName: String): List<MovieWithActors>*/

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(movies: List<Movie>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(movie: Movie): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovieToActorCrossRef(crossRef: MovieToActorCrossRef)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovieToGenreCrossRef(crossRef: MovieToGenreCrossRef)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertGenreToMovieCrossRef(crossRef: MovieToGenreCrossRef)

    @Delete
    fun deleteMovieToGenreCrossRef(crossRef: MovieToGenreCrossRef)

    @Update
    fun update(movie: Movie)

    @Delete
    fun delete(movie: Movie)

    @Query("DELETE FROM movies WHERE id = :movieId")
    fun deleteById(movieId: Long)

    @Query("DELETE FROM movies")
    fun deleteAll()
}