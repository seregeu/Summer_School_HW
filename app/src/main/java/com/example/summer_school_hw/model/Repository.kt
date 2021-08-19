package com.example.summer_school_hw.model

import androidx.lifecycle.MutableLiveData
import com.example.summer_school_hw.BuildConfig
import com.example.summer_school_hw.model.retrofit.Common
import com.example.summer_school_hw.model.retrofit.Interface.RetrofitServices
import com.example.summer_school_hw.model.retrofit.Models_retrofit.MovieInList
import com.example.summer_school_hw.model.retrofit.Models_retrofit.Result
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MovieRepository {

    lateinit private var mService: RetrofitServices
    init{
        mService = Common.retrofitService
    }

    lateinit private var result: Result
    var popularMovies: List<MovieInList> = emptyList()

    fun getPopularMovieList(): List<MovieInList> {
        mService.getPopularMovies().enqueue(object : Callback<Result> {
            override fun onFailure(call: Call<Result>, t: Throwable) {

            }

            override fun onResponse(call: Call<Result>, response: Response<Result>) {
                val movies = response.body() as Result
                popularMovies = movies.results
            }
        })
        return popularMovies
    }
}