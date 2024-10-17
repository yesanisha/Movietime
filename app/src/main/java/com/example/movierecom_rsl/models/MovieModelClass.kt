package com.example.movierecom_rsl.models

data class MovieModelClass(
    val title: String,
    val id: Int,
    val poster_path: String, // Change this to match the TMDB response
    val release_date: String,
    val overview: String,
    val vote_average: Double, // Change to match the TMDB response

)
