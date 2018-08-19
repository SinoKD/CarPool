package com.carpool.di

import com.carpool.ui.carlist.CarListActivity
import com.carpool.ui.carlist.di.CarListModule
import com.carpool.ui.carlist.di.FragmentProviderModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {
    @ContributesAndroidInjector(modules = arrayOf(CarListModule::class, FragmentProviderModule::class))
    internal abstract fun bindMainActivity(): CarListActivity
}