package com.carpool.ui.carlist.di

import com.carpool.apiservice.APIInterface
import com.carpool.ui.carlist.CarListActivity
import com.carpool.ui.carlist.CarListPresenter
import dagger.Module
import dagger.Provides

@Module
abstract class CarListModule {

    @Module
    companion object {
        @JvmStatic
        @Provides
        fun provideArticleViewPresenter(mainView: CarListActivity, apiInterface: APIInterface) = CarListPresenter(mainView, apiInterface)
    }
}