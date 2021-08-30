package com.example.summer_school_hw.model.data.RecycleAdapters

import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import coil.load
import com.example.summer_school_hw.R
import com.example.summer_school_hw.model.data.room.entities.Movie
import com.google.android.material.imageview.ShapeableImageView
import kotlin.properties.Delegates

class GridMovieResyclerAdapter(private val listener: OnItemFilmListener): RecyclerView.Adapter<GridMovieResyclerAdapter.ViewHolder>()  {

    var movies: List<Movie> by Delegates.observable(emptyList()) { _, oldList, newList ->
        autoNotify(oldList, newList) { o, n -> o.title == n.title }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflate the custom view from xml layout file
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.movie_list_item, parent, false)
        // return the view holder
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int)  {
        val movie = movies[position]
        holder.movieName?.text = movie.title
        holder.movieCover?.load(movie.imageUrl)
        holder.movieDescription?.text=movie.description
        holder.movieRating?.rating  = movie.rateScore.toFloat()
        holder.ageLimit?.text = movie.ageRestriction.toString()+"+"
        Log.i("Bind: ", "bind, position = " + position);
        val animation = AnimationUtils.loadAnimation(holder.itemView.context,android.R.anim.slide_in_left)
        holder.itemView.startAnimation(animation)
    }

    override fun getItemCount(): Int {
        return movies.size
    }



    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),View.OnClickListener{
        var movieCover: ShapeableImageView? = null
        var movieName: TextView? = null
        var movieDescription: TextView?=null
        var movieRating: RatingBar?=null
        var ageLimit: TextView? = null

        init {
            movieCover = itemView.findViewById(R.id.list_film_cover)
            movieName = itemView.findViewById(R.id.list_film_name)
            movieDescription = itemView.findViewById(R.id.list_film_description)
            movieRating = itemView.findViewById(R.id.list_ratingBar_indicator)
            ageLimit = itemView.findViewById(R.id.list_text_age_limit)

            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val posititon: Int = adapterPosition
            if (posititon!=RecyclerView.NO_POSITION) {
                listener.onMovieClick(posititon)
            }
        }
    }
    interface OnItemFilmListener{
        fun onMovieClick(position: Int)
    }

    // this two methods useful for avoiding duplicate item
    override fun getItemId(position: Int): Long {
        return position.toLong()
    }


    override fun getItemViewType(position: Int): Int {
        return position
    }

    fun <T> RecyclerView.Adapter<*>.autoNotify(oldList: List<Movie>, newList: List<T>, compare: (T, T) -> Boolean) {

        val diff = DiffUtil.calculateDiff(object : DiffUtil.Callback() {
            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                return oldList[oldItemPosition] == newList[newItemPosition]
            }

            override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                return oldList[oldItemPosition] == newList[newItemPosition]
            }

            override fun getOldListSize() = oldList.size
            override fun getNewListSize() = newList.size
        })
        diff.dispatchUpdatesTo(this)
    }
}
