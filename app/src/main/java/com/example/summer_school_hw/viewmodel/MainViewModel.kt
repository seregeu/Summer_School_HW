package com.example.summer_school_hw.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.example.summer_school_hw.BuildConfig
import com.example.summer_school_hw.model.data.ApplicationDatabase
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
import com.example.summer_school_hw.model.retrofit.Models_retrofit.MovieCredits
import com.example.summer_school_hw.model.retrofit.Models_retrofit.MovieInList
import com.example.summer_school_hw.model.retrofit.Models_retrofit.MovieInListResult
import com.example.summer_school_hw.model.retrofit.Models_retrofit.ReleaseAnswer
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.reflect.KFunction0

class  MainViewModel: ViewModel() {
    private var genresModel = GenresModel(GenresDataSourceImpl())
    //data lists
    val moviesList: LiveData<List<Movie>> get() = _moviesList
    private val _moviesList = MutableLiveData<List<Movie>>()
    //retrofit
    private var mService: RetrofitServices

    private var moviePosition: Int = -1

    private var applicationDatabase: ApplicationDatabase? = null

    val converter = ConverterForEntities()

    init {
        mService = Common.retrofitService
        getAllMovieList()
    }

    fun getAllMovieList() {
        getPopularMovieList()
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

    fun restoreMovie()= _moviesList.value?.get(moviePosition)

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

    fun getPopularMovieList(){
        val apiKey = BuildConfig.THE_MOVIEDB_API_KEY as String
        mService.getPopularMovies(apiKey,"ru").enqueue(object : Callback<MovieInListResult> {
            override fun onFailure(call: Call<MovieInListResult>, t: Throwable) {
            }
            override fun onResponse(call: Call<MovieInListResult>, response: Response<MovieInListResult>) {
                val movies = response.body() as MovieInListResult
                getMovies(movies.results)
            }
        })
    }

    fun getMovies(list: List<MovieInList>){
        val movies = converter.MovieInListToMovieList(list)
        applicationDatabase?.movieDao()?.insertAll(movies)
        _moviesList.postValue(applicationDatabase?.movieDao()?.getAll())
        getActorsFromApi(list)
        putGenresToDB(list)
    }

    fun getActorsFromApi(list: List<MovieInList>){
        val apiKey = BuildConfig.THE_MOVIEDB_API_KEY as String
        for (movie in list) {
            mService.getMovieCredits(movie.id,apiKey,"ru").enqueue(object : Callback<MovieCredits> {
                override fun onFailure(call: Call<MovieCredits>, t: Throwable) {

                }
                override fun onResponse(call: Call<MovieCredits>, response: Response<MovieCredits>) {
                    val credits = response.body() as MovieCredits
                    putActorsToDB(credits)
                }
            })
        }
    }

    fun putGenresToDB(list: List<MovieInList>){
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

    fun putActorsToDB(castAnswer: MovieCredits){
        var actorsList = converter.actorCastListtoActorList(castAnswer.cast)
        applicationDatabase?.actorDao()?.insertAll(actorsList)
        val movieActorRelations = mutableListOf<MovieToActorCrossRef>()
        val movie_id = applicationDatabase?.movieDao()?.getMovieByMDBID(castAnswer.id)?.id!!
        for (actor in actorsList) {
            val actor_id = applicationDatabase?.actorDao()?.getActorByName(actor.name)?.id!!
            movieActorRelations.add(MovieToActorCrossRef(null,movie_id, actor_id))
        }
        movieActorRelations.forEach{
            applicationDatabase?.movieDao()?.insertMovieToActorCrossRef(it)
        }
    }
}