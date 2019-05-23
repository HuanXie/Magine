package com.example.basemvvm.data.network

import android.content.Context
import android.net.ConnectivityManager
import com.google.gson.JsonParseException
import org.json.JSONObject
import retrofit2.Response
import java.io.IOException
import java.util.UUID

open class BaseRepository {

    suspend fun <T : Any> safeApiResult(
        call: suspend () -> Response<T>,
        context: Context
    ): Result<T> {
        return if (isConnected(context)) {
            try {
                val response = call.invoke()
                if (response.isSuccessful) {
                    Result.Success(response.body()!!)
                } else {
                    try {
                        val error = JSONObject(response.errorBody()?.string())
                        Result.Error(APIError(response.code(), error.toString()))
                    } catch (e: JsonParseException) {
                        Result.Error(e)
                    }
                }
            } catch (e: IOException) {
                e.printStackTrace()
                Result.Error(IOException())
            }
        } else {
            Result.Error(IOException())
        }
    }

    fun <T : Any> safeApiResultBlocking(
        call: () -> Response<T>,
        context: Context
    ): Result<T> {
        return if (isConnected(context)) {
            try {
                val response = call.invoke()
                if (response.isSuccessful) {
                    Result.Success(response.body()!!)
                } else {
                    try {
                        val error = JSONObject(response.errorBody()?.string())
                        Result.Error(APIError(response.code(), error.toString()))
                    } catch (e: JsonParseException) {
                        Result.Error(e)
                    }
                }
            } catch (e: IOException) {
                e.printStackTrace()
                Result.Error(IOException())
            }
        } else {
            Result.Error(IOException())
        }
    }

    private fun isConnected(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = connectivityManager.activeNetworkInfo
        return activeNetwork?.isConnected ?: false
    }

    fun requestId() = UUID.randomUUID().toString()
}