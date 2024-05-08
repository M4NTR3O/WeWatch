package com.bignerdranch.android.wewatch.network

import com.bignerdranch.android.wewatch.database.OmdbResponse
import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface RetrofitInterface {
    @GET("services/rest?method=flickr.interestingness.getList")
    fun searchMovie(/*@Query("query") s: String*/): Call<OmdbResponse>

    @GET
    fun searchUrlBytes(@Url url: String): Call<ResponseBody>

    @GET("/?&apikey=63ee68a")
    fun searchMovies(@Query("text") query: String): Call<OmdbResponse>
}