package com.example.basemvvm

import com.example.basemvvm.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

open class BaseApplication : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out BaseApplication> {
        return DaggerAppComponent.builder().create(this)
    }
}
