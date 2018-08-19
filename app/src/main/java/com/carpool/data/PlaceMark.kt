package com.carpool.data

data class PlaceMark(

        val address: String,
        val coordinates: ArrayList<Double>,
        val engineType: String,
        val exterior: String,
        val fuel: Int,
        val interior: String,
        val name: String,
        val vin: String
)