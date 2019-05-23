package com.example.basemvvm.di.fragment

import com.example.basemvvm.ui.ShowDetailFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class FragmentBuilder {

    @ContributesAndroidInjector
    abstract fun contributeShowDetailFragment(): ShowDetailFragment

}
