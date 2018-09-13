package com.aionsigma.android.Ultilities

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.support.v4.app.ActivityCompat

class LocationUtils(internal var context: Context) {

    //ActivityCompat.requestPermissions(context, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
    /*double latti = location2.getLatitude();
                double longi = location2.getLongitude();
                lattitude = String.valueOf(latti);
                longitude = String.valueOf(longi);
                */ val location: Location?
        get() {
            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return null
            } else {
//                val lattitude: String
//                val longitude: String
                val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager

                val location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)
                val location1 = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
                val location2 = locationManager.getLastKnownLocation(LocationManager.PASSIVE_PROVIDER)

                return location ?: (location1 ?: location2)
            }
        }

    companion object {
        private val REQUEST_LOCATION = 1
    }
}