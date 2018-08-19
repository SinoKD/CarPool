package com.carpool.data

/**
 * @author Sino K D
 * @since 8/3/18.
 *
 *
 * Model object to handle the API error messages.
 *
 */
class Error(e: Throwable) {
    var message: String? = null

    init {
        this.message = e.message
    }

}
