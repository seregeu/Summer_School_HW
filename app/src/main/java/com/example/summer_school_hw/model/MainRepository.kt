package com.example.summer_school_hw.model

import com.example.summer_school_hw.model.data.MainRemoteData
import com.example.summer_school_hw.model.retrofit.Interface.ApiService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainRepository @Inject constructor(private val remoteData : MainRemoteData) {
    suspend fun getMovieReleaseData(movieId:Int, apiKey:String,language:String) = remoteData.getMovieReleaseData(movieId, apiKey,language)
    suspend fun getPopularMoviesList(apiKey:String,language:String) = remoteData.getPopularMoviesList(apiKey,language)
    suspend fun getMovieCreditsById(id:Int,apiKey:String,language:String) = remoteData.getMovieCreditsById(id,apiKey,language)
}