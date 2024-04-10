package com.bignerdranch.android.wewatch.database

import com.bignerdranch.android.wewatch.Movie
import com.bignerdranch.android.wewatch.network.MovieResponse
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class OmdbResponse {
    lateinit var movies: MovieResponse
}