package com.bignerdranch.android.wewatch

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.time.Year
import java.util.Date
import java.util.UUID

@Entity
data class Movie (@PrimaryKey val id: UUID = UUID.randomUUID(),
             var Title: String = "",
             var Year: String = "",
             var Genre: String = "",
             var Poster: String = "",
             var isWatched: Boolean = false){
}