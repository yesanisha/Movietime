package com.example.movierecom_rsl.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movierecom_rsl.R
import com.example.movierecom_rsl.models.MovieModelClass

class MovieAdapter(private val movies: List<MovieModelClass>) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val movieTitle: TextView = itemView.findViewById(R.id.movieTitle)
        val moviePoster: ImageView = itemView.findViewById(R.id.moviePoster)
        val movieReleaseDate: TextView = itemView.findViewById(R.id.movieReleaseDate)
        val movieOverview: TextView = itemView.findViewById(R.id.movieOverview)
        val movieRating: TextView = itemView.findViewById(R.id.movieRating)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return MovieViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val currentMovie = movies[position]
        holder.movieTitle.text = currentMovie.title
        holder.movieReleaseDate.text = currentMovie.release_date
        holder.movieOverview.text = currentMovie.overview
        holder.movieRating.text = currentMovie.vote_average.toString()

        // Load image using Glide
        val imageUrl = "https://image.tmdb.org/t/p/w500${currentMovie.poster_path}" // Append the path
        Glide.with(holder.itemView.context)
            .load(imageUrl)
            .into(holder.moviePoster)
    }

    override fun getItemCount(): Int {
        return movies.size
    }
}