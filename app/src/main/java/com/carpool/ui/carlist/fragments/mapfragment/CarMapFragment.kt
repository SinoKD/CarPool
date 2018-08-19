package com.carpool.ui.carlist.fragments.mapfragment

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Bitmap.createBitmap
import android.graphics.Canvas
import android.os.Build
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.carpool.R
import com.carpool.base.BaseFragment
import com.carpool.data.PlaceMark
import com.carpool.util.AppConstants.APIConstants.MULTIPLE_PERMISSION_REQUEST
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_CAR_ADDRESS_LIST = "car_address_list"

@SuppressLint("StaticFieldLeak")
private lateinit var fusedLocationClient: FusedLocationProviderClient

private var isMarkersHidden: Boolean = false

/**
 * A simple [Fragment] subclass.
 * Use the [CarMapFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class CarMapFragment : BaseFragment(), OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    private var carListString: String? = null

    private var carList: ArrayList<PlaceMark>? = null
    private var markerList: ArrayList<Marker>? = null
    private var latLngBuilder: LatLngBounds.Builder? = null
    private var map: GoogleMap? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val listType = object : TypeToken<List<PlaceMark>>() {}.type
        arguments?.let {
            carListString = it.getString(ARG_CAR_ADDRESS_LIST)
            carList = Gson().fromJson(carListString, listType)

        }

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(context!!)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_car_map, container, false)

        val mapFragment = childFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment!!.getMapAsync(this)


        //Utility.openLocationSettings(context!!)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!checkPermission()) {
                requestPermission()
            }
        }

        return view
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>,
                                            grantResults: IntArray) {
        when (requestCode) {
            MULTIPLE_PERMISSION_REQUEST -> if (grantResults.isNotEmpty()) {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED && permissions[0].equals(Manifest.permission.ACCESS_FINE_LOCATION, ignoreCase = true) &&
                        grantResults[1] == PackageManager.PERMISSION_GRANTED && permissions[1].equals(Manifest.permission.ACCESS_COARSE_LOCATION, ignoreCase = true)) {

                    setUpGoogleMap()
                }
            }
        }
    }

    override fun onMapReady(googleMap: GoogleMap?) {
        map = googleMap!!
        setUpGoogleMap()
        googleMap.uiSettings.isZoomControlsEnabled = true
        googleMap.setOnMarkerClickListener(this)
        placeMarkerOnMap()
    }

    override fun onMarkerClick(marker: Marker?): Boolean {

        if (isMarkersHidden) {
            showAllMarkers()
            map!!.uiSettings.isMapToolbarEnabled = false
            return true
        } else {
            map!!.uiSettings.isMapToolbarEnabled = true
            hideOtherMarker(marker)
        }
        return false
    }

    private fun hideOtherMarker(selectedMarker: Marker?) {
        if (markerList != null)
            for (marker in markerList!!) {
                if (selectedMarker!!.position.latitude != marker.position.latitude ||
                        selectedMarker.position.latitude != marker.position.latitude) {
                    marker.isVisible = false
                }
            }

        isMarkersHidden = true
    }

    private fun showAllMarkers() {
        if (markerList != null)
            for (marker in markerList!!) {
                marker.isVisible = true
            }
        isMarkersHidden = false
    }

    private fun placeMarkerOnMap() {
        if (carList != null) {
            markerList = ArrayList()
            latLngBuilder = LatLngBounds.builder()
            for (placeMark in carList!!) {
                val latLng = LatLng(placeMark.coordinates[1], placeMark.coordinates.get(index = 0))

                val markerOptions = MarkerOptions().position(latLng)
                markerOptions.icon(bitmapDescriptorFromVector(context!!, R.drawable.car_map_icon))
                markerOptions.title(placeMark.name)
                markerList!!.add(map!!.addMarker(markerOptions))
                latLngBuilder!!.include(latLng)
            }

            if (latLngBuilder != null) {
                val latLngBounds = latLngBuilder!!.build()
                val cameraUpdate = CameraUpdateFactory.newLatLngBounds(latLngBounds, 120)
                map!!.animateCamera(cameraUpdate)
            }

        }
    }


    private fun setUpGoogleMap() {
        if (checkPermission() && map != null)
            map!!.isMyLocationEnabled = true
        else
            requestPermission()
    }

    private fun checkPermission(): Boolean {

        val result1 = ContextCompat.checkSelfPermission(context!!, Manifest.permission.ACCESS_FINE_LOCATION)
        val result2 = ContextCompat.checkSelfPermission(context!!, Manifest.permission.ACCESS_COARSE_LOCATION)

        return result1 == PackageManager.PERMISSION_GRANTED &&
                result2 == PackageManager.PERMISSION_GRANTED

    }

    private fun bitmapDescriptorFromVector(context: Context, vectorResId: Int): BitmapDescriptor {
        val vectorDrawable = ContextCompat.getDrawable(context, vectorResId)
        vectorDrawable!!.setBounds(
                0,
                0,
                vectorDrawable.intrinsicWidth,
                vectorDrawable.intrinsicHeight
        )
        val bitmap: Bitmap = createBitmap(vectorDrawable.intrinsicWidth, vectorDrawable.intrinsicHeight, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        vectorDrawable.draw(canvas)
        return BitmapDescriptorFactory.fromBitmap(bitmap)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment CarMapFragment.
         */

        @JvmStatic
        fun newInstance(carList: String) =
                CarMapFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_CAR_ADDRESS_LIST, carList)
                    }
                }
    }


    private fun requestPermission() {
        val permissions = java.util.ArrayList<String>()

        if (!ActivityCompat.shouldShowRequestPermissionRationale(activity!!, Manifest.permission.ACCESS_FINE_LOCATION)) {
            permissions.add(Manifest.permission.ACCESS_FINE_LOCATION)
        }
        if (!ActivityCompat.shouldShowRequestPermissionRationale(activity!!, Manifest.permission.ACCESS_COARSE_LOCATION)) {
            permissions.add(Manifest.permission.ACCESS_COARSE_LOCATION)
        }



        if (!permissions.isEmpty()) {
            requestPermissions(permissions.toTypedArray(), MULTIPLE_PERMISSION_REQUEST)
        }
    }


}
