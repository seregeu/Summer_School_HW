package com.example.summer_school_hw.viewmodel

import android.content.Context
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.summer_school_hw.BuildConfig
import com.example.summer_school_hw.model.MovieRepository
import com.example.summer_school_hw.model.data.ApplicationDatabase
import com.example.summer_school_hw.model.data.dto.ActorDto
import com.example.summer_school_hw.model.data.dto.GenreDto
import com.example.summer_school_hw.model.data.dto.MovieDto
import com.example.summer_school_hw.model.data.features.actors.ActorsDataSourceImpl
import com.example.summer_school_hw.model.data.features.genres.GenresDataSourceImpl
import com.example.summer_school_hw.model.data.features.movies.MoviesDataSourceImpl
import com.example.summer_school_hw.model.data.presentation.ActorsModel
import com.example.summer_school_hw.model.data.presentation.GenresModel
import com.example.summer_school_hw.model.data.presentation.MoviesModel
import com.example.summer_school_hw.model.data.room.ConverterForEntities
import com.example.summer_school_hw.model.data.room.entities.*
import com.example.summer_school_hw.model.data.room.relations.MovieToActorCrossRef
import com.example.summer_school_hw.model.data.room.relations.MovieToGenreCrossRef
import com.example.summer_school_hw.model.retrofit.Interface.RetrofitServices
import com.example.summer_school_hw.model.retrofit.Common
import com.example.summer_school_hw.model.retrofit.Models_retrofit.Result
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class  MainViewModel: ViewModel() {
    //models
    private var moviesModel = MoviesModel(MoviesDataSourceImpl())
    private var genresModel = GenresModel(GenresDataSourceImpl())
    private var actorsModel = ActorsModel(ActorsDataSourceImpl())
    //data lists
    val moviesList: LiveData<List<Movie>> get() = _moviesList
    private val _moviesList = MutableLiveData<List<Movie>>()

    //retrofit
    lateinit private var mService: RetrofitServices
    lateinit private var mRepository: MovieRepository

    private var moviePosition: Int = -1

    private var applicationDatabase: ApplicationDatabase? = null

    val converter = ConverterForEntities()

    init {
       // loadMovies()
        mService = Common.retrofitService
        mRepository = MovieRepository()
    }

    fun getAllMovieList() {
       val movies =  mRepository.getPopularMovieList()

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

    fun restoreMovie()= _moviesList.value?.get(moviePosition)

    fun restoreGenre(movie: Movie):Genre {
        val _movie = applicationDatabase?.movieDao()?.getGenreOfMovie(movie.id!!)
        return _movie?.first()!!.genres[0]
    }

    fun restoreActors(movie: Movie):List<Actor> {
        val actors = applicationDatabase?.movieDao()?.getActorsOfMovie(movie.id!!)
        return actors?.first()!!.actors
    }

    fun initDatabase(context: Context) {
        ApplicationDatabase.initDatabase(context)
        applicationDatabase = ApplicationDatabase.getInstance()!!
        putDataToDB(moviesModel.getMovies())
        _moviesList.postValue(applicationDatabase?.movieDao()?.getAll())

    }

    fun downloadMovies(){
        val movie_list=moviesModel.downloadMovies() + moviesModel.getMovies()
        applicationDatabase?.movieDao()?.deleteAll()
        putDataToDB(movie_list)
        _moviesList.postValue(applicationDatabase?.movieDao()?.getAll())
    }

    fun putDataToDB(movieDto_list:List<MovieDto>){
        if (applicationDatabase?.movieDao()?.getAll()?.size == 0) {
            applicationDatabase?.movieDao()?.insertAll(converter.movieDtoListtoMovieList(movieDto_list))
        }
        if (applicationDatabase?.genreDao()?.getAll()?.size == 0) {
            applicationDatabase?.genreDao()?.insertAll(converter.genreDtoListToGenreList(genresModel.getGenres()))
        }
        if (applicationDatabase?.actorDao()?.getAll()?.size == 0) {
            applicationDatabase?.actorDao()?.insertAll(converter.actorDtoListtoActorList(actorsModel.getActors()))
        }

        val movieActorRelations = getMovieActorRelationsFromDto(movieDto_list)
        movieActorRelations.forEach{
            applicationDatabase?.movieDao()?.insertMovieToActorCrossRef(it)
        }

        val movieGenreRelations = getMovieGenreRelationsFromDto(movieDto_list)
        movieGenreRelations.forEach{
            applicationDatabase?.movieDao()?.insertMovieToGenreCrossRef(it)
        }

    }

    fun getMovieActorRelationsFromDto(movieDtoList: List<MovieDto>):List<MovieToActorCrossRef>{
        val movieActorRelations = mutableListOf<MovieToActorCrossRef>()
        for(movie in movieDtoList){
            val movie_id = applicationDatabase?.movieDao()?.getMovieByTitle(movie.title)!!.id!!
            for (actor in movie.actors) {
                val actor_id = applicationDatabase?.actorDao()?.getActorByName(actor.name)!!.id!!
                    movieActorRelations.add(MovieToActorCrossRef(null,movie_id, actor_id))
            }
        }
        return movieActorRelations
    }

    fun getMovieGenreRelationsFromDto(movieDtoList: List<MovieDto>):List<MovieToGenreCrossRef>{
        val movieGenreRelations = mutableListOf<MovieToGenreCrossRef>()
        for(movie in movieDtoList){
            val movie_id = applicationDatabase?.movieDao()?.getMovieByTitle(movie.title)!!.id!!
            for (genre in movie.genre) {
                val genre_id = applicationDatabase?.genreDao()?.getGenreByName(genre.genreName)!!.id!!
                movieGenreRelations.add(MovieToGenreCrossRef(null,movie_id, genre_id))
            }
        }
        return movieGenreRelations
    }
}