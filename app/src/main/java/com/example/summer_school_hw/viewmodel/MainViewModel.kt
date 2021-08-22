package com.example.summer_school_hw.viewmodel

import android.content.Context
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
import com.example.summer_school_hw.model.data.room.entities.*
import com.example.summer_school_hw.model.data.room.relations.MovieToActorCrossRef
import com.example.summer_school_hw.model.data.room.relations.MovieToGenreCrossRef
import com.example.summer_school_hw.model.retrofit.Models_retrofit.MovieCredits
import com.example.summer_school_hw.model.retrofit.Models_retrofit.MovieInList
import com.example.summer_school_hw.model.retrofit.Models_retrofit.ReleaseAnswer
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
       // getAllMovieList()
    }

    fun getAllMovieList() {
    }

    fun getMovieReleaseData() : LiveData<ReleaseAnswer> {
        return liveData {
            val data = repository.getMovieReleaseData(451048,BuildConfig.THE_MOVIEDB_API_KEY,"ru")
            data.body()?.let { emit(it) }
        }
    }

    fun getMovieCreditsById(movieId: Int) : LiveData<List<Actor>> {
        return liveData {
            val data = converter.actorCastListtoActorList(repository.getMovieCreditsById(movieId,BuildConfig.THE_MOVIEDB_API_KEY,"ru").body()!!.cast)
            _moviesList.postValue(applicationDatabase?.movieDao()?.getAll())
            data.let { emit(it) }
        }
    }

    suspend fun getPopularMoviesList() {
        val data = repository.getPopularMoviesList(BuildConfig.THE_MOVIEDB_API_KEY,"ru").body()!!.results
        val movies = converter.MovieInListToMovieList(data)
        putGenresToDB(data)
        applicationDatabase?.movieDao()?.insertAll(movies)
        _moviesList.postValue(applicationDatabase?.movieDao()?.getAll())
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

    fun getMovies(list: List<MovieInList>){
        val movies = converter.MovieInListToMovieList(list)
        applicationDatabase?.movieDao()?.insertAll(movies)
        _moviesList.postValue(applicationDatabase?.movieDao()?.getAll())
        putGenresToDB(list)
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