package com.example.basemvvm.di.activity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.basemvvm.ui.MainActivity
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import com.example.basemvvm.di.viewmodel.ViewModelKey
import com.example.basemvvm.ui.MainRepository
import com.example.basemvvm.ui.MainViewModel

@Module
class MainActivityModule {

    @Provides
    fun provideMainActivity(mainActivity: MainActivity): MainActivity {
        return mainActivity
    }

    @Provides
    fun provideMainRepository(): MainRepository {
        return MainRepository()
    }

    @Provides
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    fun provideMainViewModel(
        mainActivity: MainActivity,
        viewModelProvider: ViewModelProvider.Factory
    ): ViewModel {
        return ViewModelProviders.of(mainActivity, viewModelProvider).get(MainViewModel::class.java)
    }
}
