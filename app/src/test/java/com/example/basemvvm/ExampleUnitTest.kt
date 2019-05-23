package com.example.basemvvm

import okhttp3.*
import org.junit.Test

import org.junit.Assert.*
import java.io.IOException

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    @Test
    fun okhttpClientShouldFetchDataFromEndpoint() {
        val client = OkHttpClient()
            val request = Request.Builder()
                .url("http://api.tvmaze.com/search/shows?q=girls")
                .build()

            client.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    println(e)
                    fail("it doesn't work")
                }
                override fun onResponse(call: Call, response: Response): Unit {
                    println("success")
                    println(response.body()?.string())
                }
            })

            Thread.sleep(10000)
    }


}
