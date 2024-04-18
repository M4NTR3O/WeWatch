package com.bignerdranch.android.wewatch.network

import android.content.ContentValues
import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bignerdranch.android.wewatch.Movie
import com.bignerdranch.android.wewatch.database.OmdbResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create


object RetrofitClient {
    // http://www.omdbapi.com/?i=tt3896198&apikey=63ee68a
    const val API_KEY = "63ee68a"
    const val OMDB_BASE_URL = "https://omdbapi.com/"
    const val OMDB_IMAGEURL = "http://img.omdbapi.com/"
    private val moviesApi: RetrofitInterface
    init{
        val retrofit = Retrofit.Builder()
            .baseUrl(OMDB_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
        moviesApi = retrofit.create(RetrofitInterface::class.java)
    }
    fun searchMovie(): LiveData<List<Movie>>{
        val responseLiveData: MutableLiveData<List<Movie>> = MutableLiveData()
        val flickrRequest: Call<OmdbResponse> = moviesApi.searchMovie()
        flickrRequest.enqueue(object : Callback<OmdbResponse> {
            override fun onFailure(call: Call<OmdbResponse>, t: Throwable) {
                Log.e(TAG, "Failed to fetch photos", t)
            }
            override fun onResponse(
                call: Call<OmdbResponse>,
                response: Response<OmdbResponse>
            ) {
                Log.d(TAG, "Response received")
                val omdbResponse: OmdbResponse? = response.body()
                val movieResponse: MovieResponse? = omdbResponse?.movies
                var movieItems: List<Movie> = movieResponse?.movieItems ?: mutableListOf()
                movieItems =
                    movieItems.filterNot {
                        it.Poster.isBlank()
                    }
                responseLiveData.value =
                    movieItems
            }
        })
        return responseLiveData
    }

}