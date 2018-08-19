package com.carpool.ui.carlist

import com.carpool.base.IBaseView
import com.carpool.data.PlaceMark

interface ICarList : IBaseView {
    fun onCarListLoaded(carList: ArrayList<PlaceMark>)
}