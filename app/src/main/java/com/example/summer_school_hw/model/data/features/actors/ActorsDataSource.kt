package com.example.summer_school_hw.model.data.features.actors

import com.example.summer_school_hw.model.data.dto.ActorDto

interface ActorsDataSource {
    fun getActors(): List<ActorDto>
}