package com.example.summer_school_hw.model.retrofit.Models_retrofit

import com.google.gson.annotations.SerializedName

data class MovieCredits (
    @SerializedName("id") val id : Int,
    @SerializedName("cast") val cast : List<Cast>,
    @SerializedName("crew") val crew : List<Crew>
)

data class Crew (
    @SerializedName("adult") val adult : Boolean,
    @SerializedName("gender") val gender : Int,
    @SerializedName("id") val id : Int,
    @SerializedName("known_for_department") val known_for_department : String,
    @SerializedName("name") val name : String,
    @SerializedName("original_name") val original_name : String,
    @SerializedName("popularity") val popularity : Double,
    @SerializedName("profile_path") val profile_path : String,
    @SerializedName("credit_id") val credit_id : String,
    @SerializedName("department") val department : String,
    @SerializedName("job") val job : String
)

data class Cast (
    @SerializedName("adult") val adult : Boolean,
    @SerializedName("gender") val gender : Int,
    @SerializedName("id") val id : Int,
    @SerializedName("known_for_department") val known_for_department : String,
    @SerializedName("name") val name : String,
    @SerializedName("original_name") val original_name : String,
    @SerializedName("popularity") val popularity : Double,
    @SerializedName("profile_path") val profile_path : String,
    @SerializedName("cast_id") val cast_id : Int,
    @SerializedName("character") val character : String,
    @SerializedName("credit_id") val credit_id : String,
    @SerializedName("order") val order : Int
)