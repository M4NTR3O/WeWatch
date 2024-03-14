package com.bignerdranch.android.wewatch

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView


private const val TITLE = "title_movie"
private const val YEAR = "year_movie"
class SearchActivity: AppCompatActivity() {
    private lateinit var searchResultsRecyclerView: RecyclerView
    private lateinit var adapter: SearchAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_movie)
        searchResultsRecyclerView = findViewById(R.id.rcView)

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