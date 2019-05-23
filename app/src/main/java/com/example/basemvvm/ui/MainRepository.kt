package com.example.basemvvm.ui

import com.example.basemvvm.data.BaseRepository
import com.example.basemvvm.data.SEARCH_SHOWS
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import javax.inject.Inject

class MainRepository @Inject constructor() : BaseRepository() {

    private val client = OkHttpClient()

    fun getShowsBySearch(searchString: String, callback: Callback) {
        val request = Request.Builder()
            .url("$SEARCH_SHOWS$searchString")
            .build()
        client.newCall(request).enqueue(callback)
    }
}

