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
import com.example.summer_school_hw.model.data.room.entities.*
import com.example.summer_school_hw.model.data.room.relations.MovieToActorCrossRef
import com.example.summer_school_hw.model.data.room.relations.MovieToGenreCrossRef


class  MainViewModel: ViewModel() {
    //models
    private var moviesModel = MoviesModel(MoviesDataSourceImpl())
    private var genresModel = GenresModel(GenresDataSourceImpl())
    private var actorsModel = ActorsModel(ActorsDataSourceImpl())

    //data lists
    val moviesList: LiveData<List<Movie>> get() = _moviesList
    private val _moviesList = MutableLiveData<List<Movie>>()

    private var moviePosition: Int = -1

    private var applicationDatabase: ApplicationDatabase? = null

    init {
        loadMovies()
    }

    fun loadMovies(){
        _moviesList.postValue(movieDtoListtoMovieList(moviesModel.getMovies()))
    }

    fun setMovieGenre(genre: Genre){
            val moviesByGenre= applicationDatabase?.movieDao()?.getMovieOfGenre(genre.genreName)!!.first().movies
            _moviesList.postValue(moviesByGenre)
    }



    fun getListGenres()= genreDtoListToGenreList(genresModel.getGenres())

    fun getMovies() = _moviesList.value

    fun selectMovie(_moviePosition:Int){
        moviePosition=_moviePosition
    }

    fun restoreMoviePosition()=moviePosition

    fun restoreMovie()= _moviesList.value?.get(moviePosition)

    fun restoreGenre(movieName:String):Genre {
        val movie = _moviesList.value?.get(moviePosition)
        val a = applicationDatabase?.movieDao()?.getGenreOfMovie(movieName)
        return a?.first()!!.genres[0]
    }

    fun restoreActors(movieName:String):List<Actor> {
        val movie = _moviesList.value?.get(moviePosition)
        val actors = applicationDatabase?.movieDao()?.getActorsOfMovie(movieName)
        return actors?.first()!!.actors
    }



    fun initDatabase(context: Context) {
        ApplicationDatabase.initDatabase(context)
        applicationDatabase = ApplicationDatabase.getInstance()!!
        if (applicationDatabase?.movieDao()?.getAll()?.size == 0) {
            applicationDatabase?.movieDao()?.insertAll(movieDtoListtoMovieList(moviesModel.getMovies()))
        }
        if (applicationDatabase?.genreDao()?.getAll()?.size == 0) {
            applicationDatabase?.genreDao()?.insertAll(genreDtoListToGenreList(genresModel.getGenres()))
        }
        if (applicationDatabase?.actorDao()?.getAll()?.size == 0) {
            applicationDatabase?.actorDao()?.insertAll(actorDtoListtoActorList(actorsModel.getActors()))
        }
        val movieActorRelations = getMovieActorRelationsFromDto(moviesModel.getMovies())
        movieActorRelations.forEach{
            applicationDatabase?.movieDao()?.insertMovieToActorCrossRef(it)
        }

        val movieGenreRelations = getMovieGenreRelationsFromDto(moviesModel.getMovies())
        movieGenreRelations.forEach{
            applicationDatabase?.movieDao()?.insertMovieToGenreCrossRef(it)
        }
    }

    fun downloadMovies(){
        val movie_list=moviesModel.downloadMovies() + moviesModel.getMovies()
        applicationDatabase?.movieDao()?.deleteAll()
        if (applicationDatabase?.movieDao()?.getAll()?.size == 0) {
            applicationDatabase?.movieDao()?.insertAll(movieDtoListtoMovieList(movie_list))
        }
        if (applicationDatabase?.genreDao()?.getAll()?.size == 0) {
            applicationDatabase?.genreDao()?.insertAll(genreDtoListToGenreList(genresModel.getGenres()))
        }
        if (applicationDatabase?.actorDao()?.getAll()?.size == 0) {
            applicationDatabase?.actorDao()?.insertAll(actorDtoListtoActorList(actorsModel.getActors()))
        }
        val movieActorRelations = getMovieActorRelationsFromDto(movie_list)
        movieActorRelations.forEach{
            applicationDatabase?.movieDao()?.insertMovieToActorCrossRef(it)
        }

        val movieGenreRelations = getMovieGenreRelationsFromDto(movie_list)
        movieGenreRelations.forEach{
            applicationDatabase?.movieDao()?.insertMovieToGenreCrossRef(it)
        }
        _moviesList.postValue(movieDtoListtoMovieList(movie_list))

    }

    //вынести
    fun MovieDto.toMovie() = Movie(
        id = null,
        title=title,
        description=description,
        rateScore = rateScore,
        ageRestriction=ageRestriction,
        imageUrl = imageUrl,
        posterUrl = posterUrl
    )
    fun movieDtoListtoMovieList(movieDtoList: List<MovieDto>):List<Movie>{
        var movieList = mutableListOf<Movie>()
        for(elem in movieDtoList){
            var _elem: Movie=elem.toMovie()
            movieList.add(_elem)
        }
        return movieList
    }
    fun GenreDto.toGenre() = Genre(
        id = null,
        genreName = genreName,
        restId = genreId
    )
    fun genreDtoListToGenreList(genreDtoList: List<GenreDto>):List<Genre>{
        var genreList = mutableListOf<Genre>()
        for(elem in genreDtoList){
            var _elem: Genre=elem.toGenre()
            genreList.add(_elem)
        }
        return genreList
    }
    fun ActorDto.toActor() = Actor(
        id = null,
        avatarURL = avatarURL,
        name = name
    )
    fun actorDtoListtoActorList( actorDtoList: List<ActorDto>):List<Actor>{
        var  actorList = mutableListOf<Actor>()
        for(elem in  actorDtoList){
            var _elem: Actor=elem.toActor()
            actorList.add(_elem)
        }
        return  actorList
    }

    fun getMovieActorRelationsFromDto(movieDtoList: List<MovieDto>):List<MovieToActorCrossRef>{
        val movieActorRelations = mutableListOf<MovieToActorCrossRef>()
        for(movie in movieDtoList){
            for (actor in movie.actors) {
                movieActorRelations.add(MovieToActorCrossRef(movie.title, actor.name))
            }
        }
        return movieActorRelations
    }

    fun getMovieGenreRelationsFromDto(movieDtoList: List<MovieDto>):List<MovieToGenreCrossRef>{
        val movieGenreRelations = mutableListOf<MovieToGenreCrossRef>()
        for(movie in movieDtoList){
            for (genre in movie.genre) {
                movieGenreRelations.add(MovieToGenreCrossRef(movie.title, genre.genreName))
            }
        }
        return movieGenreRelations
    }

}