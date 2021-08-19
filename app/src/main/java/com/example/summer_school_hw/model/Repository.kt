package com.example.summer_school_hw.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.summer_school_hw.BuildConfig
import com.example.summer_school_hw.model.data.room.entities.Movie
import com.example.summer_school_hw.model.retrofit.Common
import com.example.summer_school_hw.model.retrofit.Interface.RetrofitServices
import com.example.summer_school_hw.model.retrofit.Models_retrofit.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response



class MovieRepository {
    //data
    val moviesFromServ: LiveData<List<MovieInList>> get() = _moviesList
    private val _moviesList = MutableLiveData<List<MovieInList>>()

    lateinit private var mService: RetrofitServices
    lateinit var apiKey: String

    init{
        mService = Common.retrofitService
        apiKey = BuildConfig.THE_MOVIEDB_API_KEY as String
    }

    lateinit private var result: MovieInListResult
    var popularMovies= mutableListOf<MovieInList>()

    fun getPopularMovieList(): List<MovieInList> {
        mService.getPopularMovies(apiKey,"ru").enqueue(object : Callback<MovieInListResult> {
            override fun onFailure(call: Call<MovieInListResult>, t: Throwable) {

            }

            override fun onResponse(call: Call<MovieInListResult>, response: Response<MovieInListResult>) {
                val movies = response.body() as MovieInListResult
                popularMovies+=movies.results
                _moviesList.postValue(popularMovies)
            }
        })
        return popularMovies
    }

    fun getMovieCredits(): MovieCredits? {
        var credits: MovieCredits? =null
        mService.getMovieCredits(451048,apiKey,"ru").enqueue(object : Callback<MovieCredits> {
            override fun onFailure(call: Call<MovieCredits>, t: Throwable) {

            }
            override fun onResponse(call: Call<MovieCredits>, response: Response<MovieCredits>) {
                credits = response.body() as MovieCredits
            }
        })
        return credits
    }
    fun getMovieRelease(): ReleaseAnswer? {
        var release: ReleaseAnswer? =null
        mService.getMovieReleaseData(451048,apiKey,"ru").enqueue(object : Callback<ReleaseAnswer> {
            override fun onFailure(call: Call<ReleaseAnswer>, t: Throwable) {

            }

            override fun onResponse(call: Call<ReleaseAnswer>, response: Response<ReleaseAnswer>) {
                release = response.body() as ReleaseAnswer
            }
        })
        return release
    }
}