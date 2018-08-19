package com.carpool.di

import com.carpool.apiservice.APIInterface
import com.carpool.apiservice.APIService
import dagger.Module
import dagger.Provides


@Module
abstract class AppModule {
    @Module
    companion object {
        @JvmStatic
        @Provides
        fun provideAPIInterface(): APIInterface = APIService.getAPIInterface()
    }
}