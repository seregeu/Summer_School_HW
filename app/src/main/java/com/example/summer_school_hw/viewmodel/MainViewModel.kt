package com.example.summer_school_hw.viewmodel

import android.content.Context
import android.net.ConnectivityManager
import androidx.core.content.ContextCompat.getSystemService
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.summer_school_hw.BuildConfig
import com.example.summer_school_hw.model.MainRepository
import com.example.summer_school_hw.model.data.ApplicationDatabase
import com.example.summer_school_hw.model.data.features.genres.GenresDataSourceImpl
import com.example.summer_school_hw.model.data.presentation.GenresModel
import com.example.summer_school_hw.model.data.room.ConverterForEntities
import com.example.summer_school_hw.model.data.room.entities.Actor
import com.example.summer_school_hw.model.data.room.entities.Genre
import com.example.summer_school_hw.model.data.room.entities.Movie
import com.example.summer_school_hw.model.data.room.relations.MovieToGenreCrossRef
import com.example.summer_school_hw.model.retrofit.Models_retrofit.MovieInList
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(val repository: MainRepository) : ViewModel(){
    private var genresModel = GenresModel(GenresDataSourceImpl())
    //data lists
    val moviesList: LiveData<List<Movie>> get() = _moviesList
    private val _moviesList = MutableLiveData<List<Movie>>()
    //retrofit
    private var moviePosition: Int = -1

    private var applicationDatabase: ApplicationDatabase? = null

    val converter = ConverterForEntities()

    init {
    }

    fun getMovieCreditsById(movieId: Int) : LiveData<List<Actor>> {
        return liveData {
            val data = repository.getMovieCreditsById(movieId,BuildConfig.THE_MOVIEDB_API_KEY,"ru").body()!!
            val actors = converter.actorCastListtoActorList(data.cast)
            //_moviesList.postValue(applicationDatabase?.movieDao()?.getAll())
            data.let { emit(actors) }
        }
    }

    suspend fun getPopularMoviesList() {
        val data = repository.getPopularMoviesList(BuildConfig.THE_MOVIEDB_API_KEY,"ru").body()!!.results
        val movies = converter.MovieInListToMovieList(data)
        //applicationDatabase?.movieDao()?.insertAll(movies)
       // putGenresToDBRel(data)
        _moviesList.postValue(movies)
    }

    fun putGenresToDB(){
        if (applicationDatabase?.genreDao()?.getAll()?.size == 0) {
            applicationDatabase?.genreDao()?.insertAll(converter.genreDtoListToGenreList(genresModel.getGenres()))
        }
    }

    fun setMovieGenre(genre: Genre){
        val moviesByGenre= applicationDatabase?.movieDao()?.getMovieOfGenre(genre.id!!)?.first()!!.movies
            _moviesList.postValue(moviesByGenre)
    }

    fun getListGenres()= applicationDatabase?.genreDao()?.getAll()!!

    fun getMovies() = _moviesList.value

    fun selectMovie(_moviePosition:Int){
        moviePosition=_moviePosition
    }

    fun restoreMoviePosition()=moviePosition

    fun restoreMovie():Movie? {
        return _moviesList.value?.get(moviePosition)
    }

    fun restoreGenre(movie: Movie): Genre? {
        val _movie = movie.id?.let { applicationDatabase?.movieDao()?.getGenreOfMovie(it) }
        return _movie?.first()?.genres?.get(0)
    }

    fun restoreActors(movie: Movie):List<Actor> {
        val actors = movie.id?.let { applicationDatabase?.movieDao()?.getActorsOfMovie(it) }
        return actors?.first()!!.actors
    }

    fun initDatabase(context: Context) {
        ApplicationDatabase.initDatabase(context)
        applicationDatabase = ApplicationDatabase.getInstance()!!
        putGenresToDB()
    }

    fun putGenresToDBRel(list: List<MovieInList>){
        val movieGenreRelations = mutableListOf<MovieToGenreCrossRef>()
        for(movie in list){
            val movie_id = applicationDatabase?.movieDao()?.getMovieByTitle(movie.title)!!.id!!
            for (genre in movie.genreIDS) {
                try{ val genre_id = applicationDatabase?.genreDao()?.getGenreByRestId(genre)!!.id!!
                    movieGenreRelations.add(MovieToGenreCrossRef(null,movie_id, genre_id))
                }catch (e:NullPointerException){

                }
            }
        }
        movieGenreRelations.forEach{
            applicationDatabase?.movieDao()?.insertMovieToGenreCrossRef(it)
        }
    }

}