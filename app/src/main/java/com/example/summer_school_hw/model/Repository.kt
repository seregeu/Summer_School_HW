package com.example.summer_school_hw.model

import com.example.summer_school_hw.BuildConfig
import com.example.summer_school_hw.model.retrofit.Common
import com.example.summer_school_hw.model.retrofit.Interface.RetrofitServices
import com.example.summer_school_hw.model.retrofit.Models_retrofit.MovieInList
import com.example.summer_school_hw.model.retrofit.Models_retrofit.MovieInListResult
import com.example.summer_school_hw.model.retrofit.Models_retrofit.MovieCredits
import com.example.summer_school_hw.model.retrofit.Models_retrofit.ReleaseAnswer
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MovieRepository {

    lateinit private var mService: RetrofitServices
    lateinit var apiKey: String
    init{
        mService = Common.retrofitService
        apiKey = BuildConfig.THE_MOVIEDB_API_KEY as String

    }

    lateinit private var result: MovieInListResult
    var popularMovies: List<MovieInList> = emptyList()

    fun getPopularMovieList(): List<MovieInList> {
        mService.getPopularMovies(apiKey,"ru").enqueue(object : Callback<MovieInListResult> {
            override fun onFailure(call: Call<MovieInListResult>, t: Throwable) {

            }

            override fun onResponse(call: Call<MovieInListResult>, response: Response<MovieInListResult>) {
                val movies = response.body() as MovieInListResult
                popularMovies = movies.results
            }
        })
        return popularMovies
    }

    fun getMovieCredits(){
        mService.getMovieCredits(451048,apiKey,"ru").enqueue(object : Callback<MovieCredits> {
            override fun onFailure(call: Call<MovieCredits>, t: Throwable) {

            }

            override fun onResponse(call: Call<MovieCredits>, response: Response<MovieCredits>) {
                val movies = response.body() as MovieCredits
            }
        })
    }
    fun getMovieRelease(){
        mService.getMovieReleaseData(451048,apiKey,"ru").enqueue(object : Callback<ReleaseAnswer> {
            override fun onFailure(call: Call<ReleaseAnswer>, t: Throwable) {

            }

            override fun onResponse(call: Call<ReleaseAnswer>, response: Response<ReleaseAnswer>) {
                val movies = response.body() as ReleaseAnswer
            }
        })
    }
}