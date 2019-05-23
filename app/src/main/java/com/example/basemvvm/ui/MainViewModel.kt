package com.example.basemvvm.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.basemvvm.data.datamodels.ShowInfo
import com.example.basemvvm.extensions.setOrPost
import com.google.gson.Gson
import com.google.gson.JsonParseException
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainViewModel @Inject constructor() :
    ViewModel() {

    @Inject
    lateinit var mainRepository: MainRepository

    var selectPosition = 0

    private val _showInfo = MutableLiveData<List<ShowInfo>>().apply { value = emptyList() }
    val showInfo: LiveData<List<ShowInfo>> = _showInfo

    private val _showError = MutableLiveData<Boolean>().apply { value = false }
    val showError: LiveData<Boolean> = _showError

    private val showListCallBack = object : Callback {
        override fun onFailure(call: Call, e: IOException) {
            e.printStackTrace()
            _showError.setOrPost(true)
            _showLoading.setOrPost(false)
        }

        override fun onResponse(call: Call, response: Response) {
            try {
                val showList = Gson().fromJson(response.body()?.string(), Array<ShowInfo>::class.java).toList()
                _showInfo.setOrPost(showList)
                _showLoading.setOrPost(false)
            } catch (e: JsonParseException) {
                e.printStackTrace()
            }
        }
    }

    private val _showLoading = MutableLiveData<Boolean>().apply { value = false }
    val showLoading: LiveData<Boolean> = _showLoading
    fun getShowList(searchString: String) {
        mainRepository.getShowsBySearch(searchString, showListCallBack)
    }

    fun adjustLoadingIcon(show: Boolean) {
        _showLoading.setOrPost(show)
    }
}
