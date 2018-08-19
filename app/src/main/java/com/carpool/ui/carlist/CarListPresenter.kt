package com.carpool.ui.carlist

import com.carpool.apiservice.APIInterface
import com.carpool.base.IBasePresenter
import com.carpool.base.IBaseView
import com.carpool.data.CarListResponse
import com.carpool.data.Error
import com.carpool.data.PlaceMark
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class CarListPresenter(view: IBaseView, apiInterface: APIInterface) : IBasePresenter, ICarListPresenterContract {

    private var carListView: IBaseView? = null
    private var apiInterface: APIInterface? = null

    init {
        this.apiInterface = apiInterface
        this.carListView = view
    }

    override fun fetchCarList() {
        //Load car list from server.
        carListView!!.showLoader()

        getApiResponseHandler()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map({ carListResponse -> carListResponse.placeMarks })
                .subscribe(object : DisposableSingleObserver<ArrayList<PlaceMark>>() {
                    override fun onSuccess(placeMarks: ArrayList<PlaceMark>) {
                        carListView!!.hideLoader()
                        (carListView as ICarList).onCarListLoaded(placeMarks)
                    }

                    override fun onError(e: Throwable) {
                        carListView!!.hideLoader()
                        carListView!!.onError(Error(e))
                    }
                })
    }

    override fun getApiResponseHandler(): Single<CarListResponse> {

        return apiInterface!!.getCarList()
    }


    override fun onDestroy() {
        this.carListView = null
        this.apiInterface = null
    }
}

internal interface ICarListPresenterContract {
    fun fetchCarList()
    fun getApiResponseHandler(): Single<CarListResponse>
}