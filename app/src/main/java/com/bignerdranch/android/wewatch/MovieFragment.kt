package com.bignerdranch.android.wewatch

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bignerdranch.android.wewatch.network.RetrofitClient
import com.bignerdranch.android.wewatch.network.RetrofitInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

private const val TAG = "MovieFragment"

class MovieFragment: Fragment() {
    private lateinit var photoRecyclerView: RecyclerView
    override fun onCreate(savedInstanceState:
                          Bundle?) {
        super.onCreate(savedInstanceState)

        val searchLiveData: LiveData<String> = SearchActivity().searchContents()
        searchLiveData.observe(
            this,
            Observer { responseString -> Log.d(TAG, "Response received:$responseString")
            })
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view =
            inflater.inflate(R.layout.fragment_movie_list, container, false)
        photoRecyclerView =
            view.findViewById(R.id.photo_recycler_view)
        photoRecyclerView.layoutManager =
            GridLayoutManager(context, 3)
        return view
    }
    companion object {
        fun newInstance() =
            MovieFragment()
    }

}