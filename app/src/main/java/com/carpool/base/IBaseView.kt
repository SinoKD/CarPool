package com.carpool.base

import com.carpool.data.Error

/**
 * Created by Sino on 31/03/16.
 */
interface IBaseView {

    /**
     * callback to reflect error to view
     *
     * @param error
     */
    fun onError(error: Error)


    /**
     * callback to reflect session expired
     *
     * @param msg
     */
    fun onInvalidSession(msg: String)

    /**
     * to show overlay progress loader
     */
    fun showLoader()

    /**
     * to hide overlay progress loader
     */
    fun hideLoader()
}
