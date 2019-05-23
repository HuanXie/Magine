package com.example.basemvvm.data.network

import com.example.basemvvm.data.SEARCH_SHOWS
import com.example.basemvvm.data.datamodels.ShowInfo
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface AppApi {

    @GET(SEARCH_SHOWS)
    fun getShowsBySearch(
        @Query("q") query: String
    ):Call<List<ShowInfo>>
}