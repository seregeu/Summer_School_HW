package com.example.summer_school_hw.data.features.actors

import com.example.summer_school_hw.R
import com.example.summer_school_hw.data.dto.ActorDto

class ActorsDataSourceImpl :ActorsDataSource{
    override fun getActors() = listOf(
    ActorDto(R.drawable.image_actor_jason_stateham,"Джейсон Стэйтем"),
    ActorDto(R.drawable.image_actor_holt_mccallany,"Холт Маккэллани"),
    ActorDto(R.drawable.image_actor_josh_hartnett,"Джош Харнетт")
    )
}