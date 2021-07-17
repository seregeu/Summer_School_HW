package com.example.summer_school_hw

import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RatingBar
import android.widget.TextView
import coil.load
import com.example.summer_school_hw.data.dto.MovieDto
import com.google.android.material.imageview.ShapeableImageView

class GridMovieAdapter(private val movies: List<MovieDto>,
                       private val listener: OnItemFilmListener): RecyclerView.Adapter<GridMovieAdapter.ViewHolder>()  {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflate the custom view from xml layout file
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.movie_lits_item, parent, false)
        // return the view holder
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int)  {
        val movie = movies[position]
        holder.movie_name?.text = movie.title
        holder.movie_cover?.load(movie.imageUrl)
        holder.movie_description?.text=movie.description
        holder.movie_rating?.rating  = movie.rateScore.toFloat()
        holder.age_limit?.text = movie.ageRestriction.toString()+"+"
        Log.i("Bind: ", "bind, position = " + position);
    }

    override fun getItemCount(): Int {
        return movies.size
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),View.OnClickListener{
        var movie_cover: ShapeableImageView? = null
        var movie_name: TextView? = null
        var movie_description: TextView?=null
        var movie_rating: RatingBar?=null
        var age_limit: TextView? = null

        init {
            movie_cover = itemView.findViewById(R.id.list_film_cover)
            movie_name = itemView.findViewById(R.id.list_film_name)
            movie_description = itemView.findViewById(R.id.list_film_description)
            movie_rating = itemView.findViewById(R.id.list_ratingBar_indicator)
            age_limit = itemView.findViewById(R.id.list_text_age_limit)

            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val posititon: Int = adapterPosition
            if (posititon!=RecyclerView.NO_POSITION) {
                listener.onItemClick(posititon,1)
            }
        }
    }
    interface OnItemFilmListener{
        fun onItemClick(position: Int,mode:Int)
    }

    // this two methods useful for avoiding duplicate item
    override fun getItemId(position: Int): Long {
        return position.toLong()
    }


    override fun getItemViewType(position: Int): Int {
        return position
    }

}

