package com.example.summer_school_hw.model.data

import com.example.summer_school_hw.model.retrofit.Interface.ApiService
import javax.inject.Inject

class MainRemoteData @Inject constructor(private val apiService : ApiService) {
    suspend fun getMovieReleaseData(movieId:Int, apiKey:String,language:String) = apiService.getMovieReleaseData(movieId, apiKey,language)
    suspend fun getPopularMoviesList(apiKey:String,language:String) = apiService.getPopularMoviesList(apiKey,language)
    suspend fun getMovieCreditsById(id:Int, apiKey:String,language:String) = apiService.getMovieCreditsById(id,apiKey,language)
}