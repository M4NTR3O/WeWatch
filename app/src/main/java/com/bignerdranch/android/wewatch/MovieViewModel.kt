package com.bignerdranch.android.wewatch

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

class MovieViewModel: ViewModel() {
    val galleryItemLiveData: LiveData<List<Movie>>
    init {
        galleryItemLiveData =
            SearchActivity().searchContents()
    }
}