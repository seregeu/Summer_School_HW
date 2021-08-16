package com.example.summer_school_hw.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
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
import com.example.summer_school_hw.model.retrofit.Interface.RetrofitServices
import com.example.summer_school_hw.model.retrofit.Common
import com.example.summer_school_hw.model.retrofit.Models_retrofit.MovieInList
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
    lateinit private var mService: RetrofitServices

    //data lists
    val moviesList: LiveData<List<Movie>> get() = _moviesList
    private val _moviesList = MutableLiveData<List<Movie>>()

    private var moviePosition: Int = -1

    private var applicationDatabase: ApplicationDatabase? = null

    val converter = ConverterForEntities()


    init {
        mService = Common.retrofitService
        loadMovies()
    }

    fun loadMovies(){
        _moviesList.postValue(converter.movieDtoListtoMovieList(moviesModel.getMovies()))

    }

     fun getAllMovieList() {
        mService.getPopularMovies().enqueue(object : Callback<MutableList<MovieInList>> {
            override fun onFailure(call: Call<MutableList<MovieInList>>, t: Throwable) {

            }

            override fun onResponse(call: Call<MutableList<MovieInList>>, response: Response<MutableList<MovieInList>>) {
               val movies = response.body() as MutableList<MovieInList>
            }
        })
    }

    fun setMovieGenre(genre: Genre){
            val moviesByGenre= applicationDatabase?.movieDao()?.getMovieOfGenre(genre.genreName)!!.first().movies
            _moviesList.postValue(moviesByGenre)
    }

    fun getListGenres()= converter.genreDtoListToGenreList(genresModel.getGenres())

    fun getMovies() = _moviesList.value

    fun selectMovie(_moviePosition:Int){
        moviePosition=_moviePosition
    }

    fun restoreMoviePosition()=moviePosition

    fun restoreMovie()= _moviesList.value?.get(moviePosition)

    fun restoreGenre(movieName:String):Genre {
        val a = applicationDatabase?.movieDao()?.getGenreOfMovie(movieName)
        return a?.first()!!.genres[0]
    }

    fun restoreActors(movieName:String):List<Actor> {
        val actors = applicationDatabase?.movieDao()?.getActorsOfMovie(movieName)
        return actors?.first()!!.actors
    }

    fun initDatabase(context: Context) {
        ApplicationDatabase.initDatabase(context)
        applicationDatabase = ApplicationDatabase.getInstance()!!
        putDataToDB(moviesModel.getMovies())
    }

    fun downloadMovies(){
        val movie_list=moviesModel.downloadMovies() + moviesModel.getMovies()
        applicationDatabase?.movieDao()?.deleteAll()
        _moviesList.postValue(converter.movieDtoListtoMovieList(movie_list))
        putDataToDB(movie_list)
    }

    fun putDataToDB(movie_list:List<MovieDto>){
        if (applicationDatabase?.movieDao()?.getAll()?.size == 0) {
            applicationDatabase?.movieDao()?.insertAll(converter.movieDtoListtoMovieList(movie_list))
        }
        if (applicationDatabase?.genreDao()?.getAll()?.size == 0) {
            applicationDatabase?.genreDao()?.insertAll(converter.genreDtoListToGenreList(genresModel.getGenres()))
        }
        if (applicationDatabase?.actorDao()?.getAll()?.size == 0) {
            applicationDatabase?.actorDao()?.insertAll(converter.actorDtoListtoActorList(actorsModel.getActors()))
        }
        val movieActorRelations = converter.getMovieActorRelationsFromDto(movie_list)
        movieActorRelations.forEach{
            applicationDatabase?.movieDao()?.insertMovieToActorCrossRef(it)
        }

        val movieGenreRelations = converter.getMovieGenreRelationsFromDto(movie_list)
        movieGenreRelations.forEach{
            applicationDatabase?.movieDao()?.insertMovieToGenreCrossRef(it)
        }
    }
}