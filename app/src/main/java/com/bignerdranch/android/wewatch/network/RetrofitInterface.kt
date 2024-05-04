package com.bignerdranch.android.wewatch.network

import com.bignerdranch.android.wewatch.database.OmdbResponse
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitInterface {
    @GET("services/rest/?method=omdbapi"
            + "&api_key=63ee68a" +
            "&format=json" +
            "&nojsoncallback=1" +
            "&extras=url_s")
    fun searchMovie(/*@Query("query") s: String*/): Call<String>

}