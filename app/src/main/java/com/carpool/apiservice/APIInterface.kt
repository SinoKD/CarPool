package com.carpool.apiservice

import com.carpool.data.CarListResponse
import io.reactivex.Single
import retrofit2.http.GET


/**
 * @author Sino K D
 * @since 8/1/18.
 */
interface APIInterface {
    @GET("locations.json")
    fun getCarList(): Single<CarListResponse>
}