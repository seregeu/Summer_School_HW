package com.example.summer_school_hw.model.retrofit

import com.example.summer_school_hw.model.retrofit.Interface.RetrofitServices

object Common {
    private val BASE_URL = "https://api.themoviedb.org/3/"
    val retrofitService: RetrofitServices
        get() = RetrofitClient.getClient(com.example.summer_school_hw.model.retrofit.Common.BASE_URL).create(RetrofitServices::class.java)
}

