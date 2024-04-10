package com.bignerdranch.android.wewatch

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
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

private const val TAG = "PhotoGalleryFragment"
private const val TITLE = "title_movie"
private const val YEAR = "year_movie"
class SearchActivity: AppCompatActivity() {
    private lateinit var searchResultsRecyclerView: RecyclerView
    private lateinit var adapter: SearchAdapter
    private val title = intent.getStringExtra(TITLE)
    private val year = intent.getStringExtra(YEAR)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_movie)
        searchResultsRecyclerView = findViewById(R.id.rcView)
        val omdbLiveData: LiveData<String> = RetrofitClient.searchMovie()
        omdbLiveData.observe(
            this,
            Observer { responseString ->
                Log.d(TAG, "Response received:$responseString")
            })

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