package com.carpool.data

import com.google.gson.annotations.SerializedName

data class CarListResponse(

        @SerializedName("placemarks")
        var placeMarks: ArrayList<PlaceMark>
)