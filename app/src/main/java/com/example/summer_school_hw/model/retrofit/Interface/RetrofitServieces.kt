package com.example.summer_school_hw.model.retrofit.Interface

import com.example.summer_school_hw.model.retrofit.Models_retrofit.MovieInListResult
import com.example.summer_school_hw.model.retrofit.Models_retrofit.MovieCredits
import com.example.summer_school_hw.model.retrofit.Models_retrofit.ReleaseAnswer
import retrofit2.Call
import retrofit2.http.*

interface RetrofitServices {
    //popular movies list
    //@GET("movie/popular?api_key=18bf6f97a86842e64b836cd0c919dca0&language=ru")
    @GET("movie/popular")
    fun getPopularMovies(@Query("api_key") apiKey: String,@Query("language") lang:String): Call<MovieInListResult>
    //movie credits (actors here)
    @GET("movie/{id}/credits")
    fun getMovieCredits(@Path("id") movieId:Int,@Query("api_key") apiKey: String,@Query("language") lang:String):Call<MovieCredits>
    //moviesRelease (age here)
    @GET("movie/{id}/release_dates")
    fun getMovieReleaseData(@Path("id") movieId:Int,@Query("api_key") apiKey: String,@Query("language") lang:String):Call<ReleaseAnswer>

}