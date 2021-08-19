package com.example.summer_school_hw.model.retrofit.Models_retrofit

import com.google.gson.annotations.SerializedName

data class ReleaseAnswer (
    @SerializedName("id") val id : Int,
    @SerializedName("results") val results : List<ReleaseResults>
)
data class ReleaseResults (

    @SerializedName("iso_3166_1") val iso_3166_1 : String,
    @SerializedName("release_dates") val release_dates : List<ReleaseDates>
)
data class ReleaseDates (

    @SerializedName("certification") val certification : String,
    @SerializedName("iso_639_1") val iso_639_1 : String,
    @SerializedName("note") val note : String,
    @SerializedName("release_date") val release_date : String,
    @SerializedName("type") val type : Int
)