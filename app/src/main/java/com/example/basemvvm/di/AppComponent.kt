package com.example.basemvvm.di

import com.example.basemvvm.BaseApplication
import com.example.basemvvm.di.AppModule
import com.example.basemvvm.di.NetworkModule
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import com.example.basemvvm.di.activity.ActivityBuilder
import com.example.basemvvm.di.fragment.FragmentBuilder
import javax.inject.Singleton

@Singleton
@Component(
    modules = [NetworkModule::class,
        AppModule::class,
        AndroidSupportInjectionModule::class,
        ActivityBuilder::class,
        FragmentBuilder::class]
)
interface AppComponent : AndroidInjector<BaseApplication> {
    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<BaseApplication>()
}