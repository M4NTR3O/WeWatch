package com.bignerdranch.android.wewatch

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class SearchAdapter(var movieList: List<Movie>, var context: Context, var listener: SearchActivity.RecyclerItemListener) : RecyclerView.Adapter<SearchAdapter.SearchMoviesHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchMoviesHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.list_item_movie, parent, false)

        val viewHolder = SearchMoviesHolder(view)
        view.setOnClickListener { v -> listener.onItemClick(v, viewHolder.adapterPosition) }
        return viewHolder
    }

    override fun onBindViewHolder(holder: SearchMoviesHolder, position: Int) {

        holder.titleTextView.text = movieList[position].Title
        holder.releaseDateTextView.text = movieList[position].Year
        holder.overviewTextView.text = movieList[position].Genre

        if (movieList[position].Poster != null) {
            Picasso.get().load("https://image.tmdb.org/t/p/w500/" + movieList[position].Poster).into(holder.movieImageView)
        }
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    fun getItemAtPosition(pos: Int): Movie {
        return movieList[pos]
    }

    inner class SearchMoviesHolder(v: View) : RecyclerView.ViewHolder(v) {

        var titleTextView: TextView = v.findViewById(R.id.title)
        var overviewTextView: TextView = v.findViewById(R.id.genres)
        var releaseDateTextView: TextView = v.findViewById(R.id.Year)
        var movieImageView: ImageView = v.findViewById(R.id.Poster)

        init {
            v.setOnClickListener { v: View ->
                listener.onItemClick(v, adapterPosition)
            }
        }
    }
}