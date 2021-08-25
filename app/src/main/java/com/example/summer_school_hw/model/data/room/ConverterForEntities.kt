package com.example.summer_school_hw.model.data.room

import com.example.summer_school_hw.model.data.dto.ActorDto
import com.example.summer_school_hw.model.data.dto.GenreDto
import com.example.summer_school_hw.model.data.dto.MovieDto
import com.example.summer_school_hw.model.data.room.entities.Actor
import com.example.summer_school_hw.model.data.room.entities.Genre
import com.example.summer_school_hw.model.data.room.entities.Movie
import com.example.summer_school_hw.model.data.room.relations.MovieToActorCrossRef
import com.example.summer_school_hw.model.data.room.relations.MovieToGenreCrossRef
import com.example.summer_school_hw.model.retrofit.Models_retrofit.Cast
import com.example.summer_school_hw.model.retrofit.Models_retrofit.MovieInList

class ConverterForEntities {

    /*fun MovieDto.toMovie() = Movie(
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
            var _elem: Movie =elem.toMovie()
            movieList.add(_elem)
        }
        return movieList
    }*/
    fun GenreDto.toGenre() = Genre(
        id = null,
        genreName = genreName,
        restId = genreId
    )
    fun genreDtoListToGenreList(genreDtoList: List<GenreDto>):List<Genre>{
        var genreList = mutableListOf<Genre>()
        for(elem in genreDtoList){
            var _elem: Genre =elem.toGenre()
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
            var _elem: Actor =elem.toActor()
            actorList.add(_elem)
        }
        return  actorList
    }


    fun MovieInList.toMovie() = Movie(
        id = null,
        idMDB = id,
        title=title,
        description=overview,
        rateScore = (voteAverage/2).toInt(),
        ageRestriction=if (adult){18}else{12},
        imageUrl = "https://image.tmdb.org/t/p/original"+posterPath,
        posterUrl = "https://image.tmdb.org/t/p/original"+backdropPath
    )

    fun MovieInListToMovieList(movieInlis:List<MovieInList>):List<Movie>{
        var movieList = mutableListOf<Movie>()
        for(elem in movieInlis){
            var _elem: Movie =elem.toMovie()
            movieList.add(_elem)
        }
        return movieList
    }

    fun Cast.toActor() = Actor(
        id = null,
        avatarURL = "https://image.tmdb.org/t/p/original"+profile_path,
        name = name
    )

    fun actorCastListtoActorList( actorCastList: List<Cast>):List<Actor>{
        var  actorList = mutableListOf<Actor>()
        for(elem in  actorCastList){
            var _elem: Actor =elem.toActor()
            actorList.add(_elem)
        }
        return  actorList
    }
}