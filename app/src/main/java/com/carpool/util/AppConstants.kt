package com.carpool.util


class AppConstants {

    /**
     * Constants related to API
     * The API end points, json tag names to parse the result.
     */
    object APIConstants {

        //Base URL
        const val BASE_URL: String = "https://s3-us-west-2.amazonaws.com/wunderbucket/"
        const val TIME_OUT: Long = 30 * 1000
        const val MULTIPLE_PERMISSION_REQUEST = 210
    }
}