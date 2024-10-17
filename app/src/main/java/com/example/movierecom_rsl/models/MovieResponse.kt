package com.example.movierecom_rsl.models

data class MovieResponse(
    val page: Int,
    val results: List<MovieModelClass>,
    val total_pages: Int,
    val total_results: Int
)