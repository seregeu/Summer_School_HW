package com.example.summer_school_hw.data.RecycleAdapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.summer_school_hw.R
import com.example.summer_school_hw.data.dto.ActorDto

class ActorRecyclerAdapter(private val actors: List<ActorDto>) :
    RecyclerView.Adapter<ActorRecyclerAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var _ImageView: ImageView? = null
        var _TextView: TextView? = null
        init {
            _ImageView = itemView.findViewById(R.id.actor_image)
            _TextView = itemView.findViewById(R.id.actor_name)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.actors_list_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val actor = actors[position]
        holder._TextView?.text = actor.name
        holder._ImageView?.setImageResource(actor.avatar)
    }

    override fun getItemCount() = actors.size
}