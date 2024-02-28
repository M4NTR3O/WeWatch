package com.bignerdranch.android.wewatch

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class AddActivity: AppCompatActivity() {
    private lateinit var movieTitle: EditText
    private lateinit var yearMovie: EditText
    private lateinit var poster: ImageView
    private lateinit var search: ImageButton
    private lateinit var add: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)
        movieTitle = findViewById(R.id.editTextTitle)
        yearMovie = findViewById(R.id.editTextYear)
        poster = findViewById(R.id.imageViewPoster)
        search = findViewById(R.id.imageButtonSearch)
        add = findViewById(R.id.buttonAddMovie)
        search.setOnClickListener(){
            val intent = Intent(this@AddActivity, SearchActivity:: class.java)
            startActivity(intent)
        }
        add.setOnClickListener(){

        }
    }
    companion object{

    }
}