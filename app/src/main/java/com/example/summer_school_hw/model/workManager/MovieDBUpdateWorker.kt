package com.example.summer_school_hw.model.workManager

import android.content.Context
import android.util.Log
import androidx.fragment.app.activityViewModels
import androidx.hilt.work.HiltWorker
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.summer_school_hw.BuildConfig
import com.example.summer_school_hw.MyApplication
import com.example.summer_school_hw.model.MainRepository
import com.example.summer_school_hw.model.data.ApplicationDatabase
import com.example.summer_school_hw.model.data.room.ConverterForEntities
import com.example.summer_school_hw.model.data.room.entities.Movie
import com.example.summer_school_hw.model.data.room.relations.MovieToActorCrossRef
import com.example.summer_school_hw.model.data.room.relations.MovieToGenreCrossRef
import com.example.summer_school_hw.model.retrofit.Models_retrofit.MovieCredits
import com.example.summer_school_hw.model.retrofit.Models_retrofit.MovieInList
import com.example.summer_school_hw.viewmodel.MainViewModel
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.*
import okhttp3.internal.Internal.instance
import javax.inject.Inject

@HiltWorker
class ExampleWorker @AssistedInject constructor(
    @Assisted appContext: Context,
    @Assisted workerParams: WorkerParameters,
) : Worker(appContext, workerParams) {
    init{
        ApplicationDatabase.initDatabase(appContext)
    }
    @Inject
    lateinit var repository: MainRepository
    val applicationDatabase = ApplicationDatabase.getInstance()!!
    val converter: ConverterForEntities= ConverterForEntities()
    override fun doWork(): Result {
        try {
            Log.d("WORKER-My","!doWork!")
            runBlocking(Dispatchers.IO) {
                coroutineScope {
                    val data = repository.getPopularMoviesList(BuildConfig.THE_MOVIEDB_API_KEY,"ru").body()!!.results
                    val movies = converter.MovieInListToMovieList(data)
                    applicationDatabase?.movieDao()?.insertAll(movies)
                    putGenresToDBRel(data)
                    applicationDatabase?.movieDao()?.insertAll(movies)
                    for (movie in data){
                        val data = repository.getMovieCreditsById(movie.id,BuildConfig.THE_MOVIEDB_API_KEY,"ru").body()!!
                        putActorsToDB(data)
                    }
                }
            }
            return Result.success()
        } catch (e: Exception) {
            return Result.failure()
        }
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