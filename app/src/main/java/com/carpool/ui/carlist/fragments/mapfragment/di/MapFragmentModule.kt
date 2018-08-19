package com.carpool.ui.carlist.fragments.mapfragment.di

import com.carpool.ui.carlist.fragments.mapfragment.CarMapFragment
import dagger.Binds
import dagger.Module

@Module
abstract class MapFragmentModule {

    @Binds
    abstract fun provideCarMapFragmentView(carMapFragment: CarMapFragment): CarMapFragment
}
