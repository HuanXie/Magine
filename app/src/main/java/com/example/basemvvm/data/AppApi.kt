package com.example.basemvvm.data

import com.example.basemvvm.data.datamodels.ShowInfo
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.*

interface AppApi {

    @GET(SEARCH_SHOWS)
    fun getShowsBySearch(
        @Query("q") query: String
    ):Call<List<ShowInfo>>
}