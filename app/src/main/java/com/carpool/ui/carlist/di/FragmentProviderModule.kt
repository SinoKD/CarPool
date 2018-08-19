package com.carpool.ui.carlist.di

import com.carpool.ui.carlist.fragments.listfragment.CarFragment
import com.carpool.ui.carlist.fragments.listfragment.di.ListFragmentModule
import com.carpool.ui.carlist.fragments.mapfragment.CarMapFragment
import com.carpool.ui.carlist.fragments.mapfragment.di.MapFragmentModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentProviderModule {
    @ContributesAndroidInjector(modules = arrayOf(ListFragmentModule::class))
    internal abstract fun provideCarFragmentFactory(): CarFragment

    @ContributesAndroidInjector(modules = arrayOf(MapFragmentModule::class))
    internal abstract fun provideCarMapFragmentFactory(): CarMapFragment
}