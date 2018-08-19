package com.carpool.util

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.location.LocationManager
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.provider.Settings
import android.support.v7.app.AlertDialog

class Utility {

    companion object {

        @JvmStatic
        fun isNetworkAvailable(context: Context): Boolean {
            val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE)
            return if (connectivityManager is ConnectivityManager) {
                val networkInfo: NetworkInfo? = connectivityManager.activeNetworkInfo
                networkInfo?.isConnected ?: false
            } else false
        }

        @JvmStatic
        fun openLocationSettings(context: Context) {
            val manager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager?
            if (!manager!!.isProviderEnabled(LocationManager.GPS_PROVIDER))
                buildAlertMessageNoGps(context)
        }

        @JvmStatic
        fun buildAlertMessageNoGps(context: Context) {
            val builder = AlertDialog.Builder(context)
            builder.setMessage("Your GPS seems to be disabled, do you want to enable it?")
                    .setCancelable(false)
                    .setPositiveButton("Yes", DialogInterface.OnClickListener { dialog, id -> context.startActivity(Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)) })
                    .setNegativeButton("No", DialogInterface.OnClickListener { dialog, id -> dialog.cancel() })
            val alert = builder.create()
            alert.show()
        }
    }
}