package com.example.wapp;

import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresPermission;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;


public class GPS {
    LocationManager lm;
    Location loc;
    double latitude;
    double longitude;

    public GPS(Context c, LocationManager lm){

       this.lm = lm;

        if ( Build.VERSION.SDK_INT >= 23 &&
                ContextCompat.checkSelfPermission( c, android.Manifest.permission.ACCESS_FINE_LOCATION ) != PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission( c, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }

       Location loc = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);

        longitude = loc.getLongitude();
        latitude = loc.getLatitude();

        final LocationListener l = new LocationListener() {
            public void onLocationChanged(Location l) {
                 longitude = l.getLongitude();
                latitude = l.getLatitude();
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };
        lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 2000, 10, l);
    }

    public double getLat(){
        return latitude;
    }

    //http://stackoverflow.com/questions/120283/how-can-i-measure-distance-and-create-a-bounding-box-based-on-two-latitudelongi/123305#123305
    public double distFrom(double lat1, double lng1) {
        double earthRadius = 6371; // miles (or 6371.0 kilometers)
        double dLat = Math.toRadians(latitude-lat1);
        double dLng = Math.toRadians(longitude-lng1);
        double sindLat = Math.sin(dLat / 2);
        double sindLng = Math.sin(dLng / 2);
        double a = Math.pow(sindLat, 2) + Math.pow(sindLng, 2)
                * Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(latitude));
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        double dist = earthRadius * c;

        return dist;
    }
}
