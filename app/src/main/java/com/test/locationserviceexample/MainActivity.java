package com.test.locationserviceexample;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.IBinder;
import android.provider.Settings;
import android.util.Log;

public class MainActivity extends Activity implements LocationListener{

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    private static final String DEFAULT_LOCATION_BACKENDS = "default_location_backends";
    private static final String MOZILLA_BACKEND = "org.microg.nlp.backend.ichnaea/org.microg.nlp.backend.ichnaea.BackendService";
    protected LocationManager locationManager;

    @Override
    public void onLocationChanged(Location locFromGps) {
        Log.e(TAG, "LOCATION CHANGED: " + locFromGps.toString());
    }


    @Override
    public void onProviderDisabled(String provider) {
        // called when the GPS provider is turned off (user turning off the GPS on the phone)
        Log.e(TAG, "Provider is disabled: " + provider);
    }

    @Override
    public void onProviderEnabled(String provider) {
        // called when the GPS provider is turned on (user turning on the GPS on the phone)
        Log.e(TAG, "Provider is enabled: " + provider);
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        // called when the status of the GPS provider changes
        Log.e(TAG, "Status changed: " + provider + status);
    }



    @Override
    protected void onStart() {
        super.onStart();
        Log.e(TAG, "onStart: ");
        requestLocation();

    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e(TAG, "onStop: ");
        locationManager.removeUpdates(this);

    }

    private void requestLocation() {
        Log.d(TAG, "requestLocation: called");
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
            try {
                locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 10, this);
            }catch (SecurityException e){
                Log.e(TAG, "requestLocation: ");
            }
        }
    }

}
