package com.example.movierecom_rsl.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movierecom_rsl.R
import com.example.movierecom_rsl.adapters.MovieAdapter
import com.example.movierecom_rsl.api.RetrofitInstance
import com.example.movierecom_rsl.models.MovieResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RecommendationFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_recommendation, container, false)
        recyclerView = view.findViewById(R.id.recyclerviewForRecommendationMovies)
        recyclerView.layoutManager = LinearLayoutManager(activity)

        fetchPopularMovies()

        return view
    }

    private fun fetchPopularMovies() {
        val apiKey = "api key" // Replace with your TMDB API key

        RetrofitInstance.api.getPopularMovies(apiKey).enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                if (response.isSuccessful) {
                    val movies = response.body()?.results ?: emptyList()
                    // Set up your adapter with the movies
                    recyclerView.adapter = MovieAdapter(movies)
                } else {
                    Log.e("TMDB API", "Response not successful: ${response.errorBody()}")
                }
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                Log.e("TMDB API", "Failure: ${t.message}")
            }
        })
    }
}
