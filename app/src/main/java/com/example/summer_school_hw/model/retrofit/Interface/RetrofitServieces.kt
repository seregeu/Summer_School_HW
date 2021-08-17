package com.example.summer_school_hw.model.retrofit.Interface

import com.example.summer_school_hw.model.retrofit.Models_retrofit.Result
import retrofit2.Call
import retrofit2.http.*

interface RetrofitServices {
    @GET("movie/popular?api_key=18bf6f97a86842e64b836cd0c919dca0&language=ru")
   // fun getPopularMovies(): Call<MutableList<MovieInList>>
    fun getPopularMovies(): Call<Result>
}