package com.example.basemvvm.extensions

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import com.example.basemvvm.BuildConfig


fun View?.setVisibleOrGone(show: Boolean) {
    if (show) visible() else gone()
}

fun View?.visible() {
    debugThrowOnNull()
    this?.visibility = View.VISIBLE
}

fun View?.gone() {
    debugThrowOnNull()
    this?.visibility = View.GONE
}

fun View?.debugThrowOnNull() {
    if (this == null && BuildConfig.DEBUG) throw RuntimeException("Tried to use null view")
}

fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
}
