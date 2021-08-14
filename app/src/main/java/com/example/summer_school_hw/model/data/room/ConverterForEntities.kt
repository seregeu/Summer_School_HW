package com.example.summer_school_hw.model.data.room

import com.example.summer_school_hw.model.data.dto.ActorDto
import com.example.summer_school_hw.model.data.dto.GenreDto
import com.example.summer_school_hw.model.data.dto.MovieDto
import com.example.summer_school_hw.model.data.room.entities.Actor
import com.example.summer_school_hw.model.data.room.entities.Genre
import com.example.summer_school_hw.model.data.room.entities.Movie
import com.example.summer_school_hw.model.data.room.relations.MovieToActorCrossRef
import com.example.summer_school_hw.model.data.room.relations.MovieToGenreCrossRef

class ConverterForEntities {

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
            var _elem: Movie =elem.toMovie()
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