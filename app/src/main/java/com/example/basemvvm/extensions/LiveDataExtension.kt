package com.example.basemvvm.extensions

import androidx.lifecycle.MutableLiveData
import android.os.Looper

fun <T> MutableLiveData<T>.setOrPost(t: T) {
    if (Looper.myLooper() == Looper.getMainLooper()) {
        value = t
    } else {
        postValue(t)
    }
}