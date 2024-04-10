package com.bignerdranch.android.wewatch.network

import com.bignerdranch.android.wewatch.Movie
import com.google.gson.annotations.SerializedName

class MovieResponse {
    @SerializedName("movie")
    lateinit var movieItems: List<Movie>
}