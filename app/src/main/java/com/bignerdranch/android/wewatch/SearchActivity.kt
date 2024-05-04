package com.bignerdranch.android.wewatch

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.bignerdranch.android.wewatch.database.OmdbResponse
import com.bignerdranch.android.wewatch.network.RetrofitClient
import com.bignerdranch.android.wewatch.network.RetrofitInterface
import io.reactivex.Observable
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

private const val TAG = "SearchActivity"
private const val TITLE = "title_movie"
private const val YEAR = "year_movie"
class SearchActivity: AppCompatActivity() {
    private val movieApi: RetrofitInterface
    init {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(RetrofitClient.OMDB_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
        movieApi = retrofit.create(RetrofitInterface::class.java)
    }
    fun searchContents(): LiveData<String> {
        val responseLiveData: MutableLiveData<String> = MutableLiveData()
        val searchRequest: Call<String> = movieApi.searchMovie()
        searchRequest.enqueue(object :
            Callback<String> {
            override fun onFailure(call: Call<String>, t: Throwable) {
                Log.e(TAG, "Failed to search movies", t)
            }
            override fun onResponse(
                call: Call<String>,
                response: Response<String>
            ) {
                Log.d(TAG, "Response received")
                responseLiveData.value =
                    response.body()
            }
        })
        return responseLiveData
    }

    private lateinit var movieListViewModel:
            MovieListViewModel
    private lateinit var searchResultsRecyclerView: RecyclerView
    private lateinit var adapter: SearchAdapter
    private val title = intent.getStringExtra(TITLE)
    private val year = intent.getStringExtra(YEAR)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_movie)
        val isFragmentContainerEmpty = savedInstanceState == null
        if (isFragmentContainerEmpty) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragmentContainer, MovieFragment.newInstance())
                .commit()
        }
        /*searchResultsRecyclerView = findViewById(R.id.rcView)
        movieListViewModel = ViewModelProviders.of(this).get(MovieListViewModel::class.java)*/
        /*movieListViewModel.movieListLiveData.observe(
            this,
            Observer {
                movieItems -> searchResultsRecyclerView.adapter = SearchAdapter(movieItems)
            }
        )*/
    }
    companion object{
        fun newIntent(packageContext: Context, titleMovie: String, yearMovie: String): Intent {
            return Intent(packageContext, SearchActivity::class.java).apply {
                putExtra(TITLE, titleMovie)
                putExtra(YEAR, yearMovie)
            }
        }
    }
    interface RecyclerItemListener {
        fun onItemClick(v: View, position: Int)
    }
}