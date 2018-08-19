package com.carpool.ui.carlist.fragments.listfragment.di

import android.arch.lifecycle.ViewModelProvider
import com.carpool.ui.carlist.fragments.listfragment.CarFragment
import dagger.Binds

import dagger.Module
import dagger.Provides

@Module
abstract class ListFragmentModule {

    @Binds
    abstract fun provideDetailFragmentView(carFragment: CarFragment): CarFragment
}
