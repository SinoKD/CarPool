package com.carpool

import com.carpool.apiservice.APIInterface
import com.carpool.data.CarListResponse
import com.carpool.data.PlaceMark
import com.carpool.ui.carlist.CarListPresenter
import com.carpool.ui.carlist.ICarList
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock


class CarListActivityUnitTest {


    private var apiInterface: APIInterface? = null

    private var carListView: ICarList? = null

    private var carListPresenter: CarListPresenter? = null

    private var carList: ArrayList<PlaceMark>? = null

    var carListResponseSingle: Single<CarListResponse>? = null

    var carListResponse: CarListResponse? = null

    @Before
    fun setUp() {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Scheduler -> Schedulers.trampoline() }

        val coordinates = ArrayList<Double>()
        coordinates.add(10.07526)
        coordinates.add(53.59301)

        val placeMark = PlaceMark("Lesserstraße 170, 22049 Hamburg",
                coordinates,
                "CE",
                "UNACCEPTABLE",
                42,
                "UNACCEPTABLE",
                "HH-GO8522",
                "WME4513341K565439"
        )

        apiInterface = mock(APIInterface::class.java)
        carListView = mock(ICarList::class.java)
        carListPresenter = CarListPresenter(carListView!!, apiInterface!!)
        carList = ArrayList()
        carList!!.add(placeMark)

        carListResponse = CarListResponse(carList!!)
        carListResponseSingle = Single.just(carListResponse)
    }


    @Test
    fun shouldOnCarListLoadedCalled() {
        Mockito.`when`(carListPresenter!!.getApiResponseHandler()).thenReturn(carListResponseSingle)
        carListPresenter!!.fetchCarList()
        Mockito.verify(carListView)!!.onCarListLoaded(carList!!)
    }
}
