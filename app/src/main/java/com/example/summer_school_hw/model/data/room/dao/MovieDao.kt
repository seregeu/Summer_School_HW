package com.example.summer_school_hw.model.data.room.dao
import androidx.room.*
import com.example.summer_school_hw.model.data.room.entities.Actor
import com.example.summer_school_hw.model.data.room.entities.Movie
import com.example.summer_school_hw.model.data.room.relations.*


@Dao
interface MovieDao {
    @Query("SELECT * FROM movies")
    fun getAll(): List<Movie>

    @Transaction
    @Query("SELECT * FROM movies WHERE movie_id = :movieId")
    fun getGenreOfMovie(movieId: Long): List<MovieWithGenres>

    @Transaction
    @Query("SELECT * FROM genres WHERE genre_id = :genreId")
    fun getMovieOfGenre(genreId: Long): List<GenreWithMovies>

    @Transaction
    @Query("SELECT * FROM movies WHERE movie_id = :movieId")
    fun getActorsOfMovie(movieId: Long): List<MovieWithActors>

    @Transaction
    @Query("SELECT * FROM movies WHERE title = :movieTitle")
    fun getMovieByTitle(movieTitle: String): Movie

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

    @Query("DELETE FROM movietogenrecrossref")
    fun deleteMovieToGenreCrossRef()

    @Query("DELETE FROM movietoactorcrossref")
    fun deleteMovieToActorCrossRef()

    @Update
    fun update(movie: Movie)

    @Delete
    fun delete(movie: Movie)

    @Query("DELETE FROM movies WHERE movie_id = :movieId")
    fun deleteById(movieId: Long)

    @Query("DELETE FROM movies")
    fun deleteAll()
}