package com.bignerdranch.android.wewatch

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity


private const val TITLE = "title_movie"
private const val YEAR = "year_movie"
class SearchActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_movie)
    }
    companion object{
        fun newIntent(packageContext: Context, titleMovie: String, yearMovie: String): Intent {
            return Intent(packageContext, SearchActivity::class.java).apply {
                putExtra(TITLE, titleMovie)
                putExtra(YEAR, yearMovie)
            }
        }
    }
}