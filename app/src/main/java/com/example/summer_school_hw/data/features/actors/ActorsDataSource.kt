package com.example.summer_school_hw.data.features.actors

import com.example.summer_school_hw.data.dto.ActorDto

interface ActorsDataSource {
    fun getActors(): List<ActorDto>
}