package com.example.movierecom_rsl

import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import java.util.*
import androidx.appcompat.app.AppCompatActivity

class MovieDetailActivity : AppCompatActivity() {

    private lateinit var selectedDateTextView: TextView
    private lateinit var savedDateMessageTextView: TextView
    private lateinit var ratingBar: RatingBar
    private lateinit var ratedMessageTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        // Get the data from the intent
        val movieTitle = intent.getStringExtra("MOVIE_TITLE") ?: "Unknown Title"
        val moviePosterId = intent.getIntExtra("MOVIE_POSTER", 0)
        val movieDescription = intent.getStringExtra("MOVIE_DESCRIPTION") ?: "No description available."
        val movieReviews = intent.getStringExtra("MOVIE_REVIEWS") ?: "No reviews available."

        // Log the received data for debugging
        Log.d("MovieDetailActivity", "Title: $movieTitle")
        Log.d("MovieDetailActivity", "Poster ID: $moviePosterId")
        Log.d("MovieDetailActivity", "Description: $movieDescription")
        Log.d("MovieDetailActivity", "Reviews: $movieReviews")

        // Find views in the layout
        val moviePosterImageView = findViewById<ImageView>(R.id.imageViewMovie)
        val movieTitleTextView = findViewById<TextView>(R.id.textViewMovieName)
        val movieDescriptionTextView = findViewById<TextView>(R.id.textViewDescription)
        val movieReviewsTextView = findViewById<TextView>(R.id.textViewRating)
        val backButton = findViewById<ImageButton>(R.id.backButton)
        val buttonDatePicker = findViewById<Button>(R.id.buttonDatePicker)
        selectedDateTextView = findViewById(R.id.textViewSavedDateMessage)
        savedDateMessageTextView = findViewById(R.id.textViewSavedDateMessage)
        ratingBar = findViewById(R.id.ratingBar)
        ratedMessageTextView = findViewById(R.id.textViewRatedMessage)

        // Set movie details
        movieTitleTextView.text = movieTitle
        moviePosterImageView.setImageResource(moviePosterId)
        movieDescriptionTextView.text = movieDescription
        movieReviewsTextView.text = movieReviews

        // Back button functionality
        backButton.setOnClickListener {
            finish()
        }

        // Date Picker functionality
        buttonDatePicker.setOnClickListener {
            showDatePickerDialog()
        }

        // Rating functionality
        ratingBar.setOnRatingBarChangeListener { _, rating, _ ->
            val ratingMessage = when (rating.toInt()) {
                0 -> "You haven't rated this movie yet."
                1 -> "You rated this movie: ⭐"
                2 -> "You rated this movie: ⭐⭐"
                3 -> "You rated this movie: ⭐⭐⭐"
                4 -> "You rated this movie: ⭐⭐⭐⭐"
                5 -> "You rated this movie: ⭐⭐⭐⭐⭐"
                else -> "Invalid rating"
            }
            ratedMessageTextView.text = ratingMessage
            ratedMessageTextView.visibility = TextView.VISIBLE
        }
    }

    private fun showDatePickerDialog() {
        // Get the current date
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        // Create a DatePickerDialog
        val datePickerDialog = DatePickerDialog(this, { _, selectedYear, selectedMonth, selectedDay ->
            // Update the TextView with the selected date
            val selectedDate = "$selectedDay/${selectedMonth + 1}/$selectedYear"
            selectedDateTextView.text = "Release Date: $selectedDate"

            // Show the saved date message
            savedDateMessageTextView.text = "DATE THAT WAS SAVED: $selectedDate IS SAVED!"
            savedDateMessageTextView.visibility = TextView.VISIBLE // Show the message
        }, year, month, day)

        datePickerDialog.show()
    }
}
