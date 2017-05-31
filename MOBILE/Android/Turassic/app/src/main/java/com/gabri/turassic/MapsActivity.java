package com.gabri.turassic;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.location.Location;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Chronometer;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import com.gabri.turassic.gps.GPSTracker;
import com.gabri.turassic.gps.GeneralUtils;
import com.gabri.turassic.gps.LocationTracker;
import com.gabri.turassic.gps.TrackerSettings;
import com.gabri.turassic.service.LocationData;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private static GoogleMap googleMap;
    private double mapCenterLatitude = 0.0;
    private double mapCenterLongitude = 0.0;
    private static double startLatitude = 0.0;
    private static double startLongitude = 0.0;
    private ArrayList<Location> locations;
    private LocationTracker tracker;
    private static GPSTracker gpsTracker;
    private ArrayList<String> speedValues;
    private static TextClock time;
    private Chronometer stopWatch;
    public static String dateString, timeString;
    private TextView display_time_textview;
    private AsyncTaskRunner runner;
    private FloatingActionButton back_floatactionbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        time = (TextClock)findViewById(R.id.map_time);
        stopWatch = (Chronometer)findViewById(R.id.chrono);
        display_time_textview=(TextView)findViewById(R.id.currenttime_textView);
        back_floatactionbutton=(FloatingActionButton)findViewById(R.id.back_floatingActionButton);
        back_floatactionbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MapsActivity.this,DangerActivity.class);
                startActivity(intent);
            }
        });
        stopWatch.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer c) {
                long elapsedMillis = SystemClock.elapsedRealtime() - c.getBase();
                if (elapsedMillis > 3600000L) {
                    c.setFormat("0%s");
                } else {
                    c.setFormat("00:%s");
                }

                display_time_textview.setText(c.getText().toString());

            }
        });
        stopWatch.setBase(SystemClock.elapsedRealtime());
        stopWatch.start();
        timeString = time.getText().toString();
        dateString = GeneralUtils.getCurrentDate();
//        trekName = trekMode + "_" + dateString;
        gpsTracker = new GPSTracker(MapsActivity.this);
        locations = new ArrayList<>();
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        runner = new AsyncTaskRunner();
        runner.execute();
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap map) {
        googleMap = map;

        try {
            MapsInitializer.initialize(this);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            googleMap.setMyLocationEnabled(true);
            googleMap.getUiSettings().setZoomControlsEnabled(false);
            googleMap.getUiSettings().setZoomGesturesEnabled(false);
//            googleMap.addMarker(new MarkerOptions().position(new LatLng(gpsTracker.getLatitude(), gpsTracker.getLongitude())).anchor(0.5f, 0.5f).icon(BitmapDescriptorFactory.fromBitmap(GeneralUtils.getMarker(this, "start"))));

            gpsTracker.stopUsingGPS();
//                    googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        } catch (SecurityException e) {
            e.printStackTrace();
        }

        if (gpsTracker.canGetLocation()) {
            startLongitude = gpsTracker.getLongitude();
            startLatitude = gpsTracker.getLatitude();
            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(new LatLng(startLatitude, startLongitude)).zoom(17).build();
            googleMap.animateCamera(CameraUpdateFactory
                    .newCameraPosition(cameraPosition));
            LocationData.getInstance().setCurrLocation(gpsTracker.getLocation());
            gpsTracker.stopUsingGPS();

        }
    }
    private void addLines() {

        PolylineOptions polylineOptions = new PolylineOptions();

        for (Location loc : locations) {
            polylineOptions.add(new LatLng(loc.getLatitude(), loc.getLongitude()));
        }

        polylineOptions.geodesic(true).width(8).color(Color.parseColor("#FF3D00"));

        googleMap.addPolyline(polylineOptions);



    }

    private class AsyncTaskRunner extends AsyncTask<String, String, String> {

        private String resp;

        @Override
        protected String doInBackground(String... param) {


            try {

                tracker = new LocationTracker(
                        MapsActivity.this,
                        new TrackerSettings()
                                .setUseGPS(true)
                                .setUseNetwork(false)
                                .setUsePassive(false)
                                .setTimeout(3 * 60 * 1000)
                                .setMetersBetweenUpdates(10)
                                .setTimeBetweenUpdates(10 * 1000)

                ) {

                    @Override
                    public void onLocationFound(Location location) {

                        mapCenterLatitude = location.getLatitude();
                        mapCenterLongitude = location.getLongitude();

                        CameraPosition cameraPosition = new CameraPosition.Builder()
                                .target(new LatLng(mapCenterLatitude, mapCenterLongitude)).zoom(17).build();
                        googleMap.animateCamera(CameraUpdateFactory
                                .newCameraPosition(cameraPosition));
                        locations.add(location);
                        addLines();
                        LocationData.getInstance().setCurrLocation(location);




                    }


                    @Override
                    public void onTimeout() {
                        // Do some stuff when a new GPS Location has been found
                    }


                };

               runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            tracker.startListening();
                        } catch (SecurityException e){
                            e.printStackTrace();
                        }
                    }
                });




            } catch (SecurityException e){
                e.printStackTrace();
            }


         return resp;
        }

        /*
         * (non-Javadoc)
         *
         * @see android.os.AsyncTask#onPostExecute(java.lang.Object)
         */
        @Override
        protected void onPostExecute(String result) {
            // execution of result of Long time consuming operation

        }

        /*
         * (non-Javadoc)
         *
         * @see android.os.AsyncTask#onPreExecute()
         */
        @Override
        protected void onPreExecute() {
            // Things to be done before execution of long running operation. For
            // example showing ProgessDialog
        }

        /*
         * (non-Javadoc)
         *
         * @see android.os.AsyncTask#onProgressUpdate(Progress[])
         */
        @Override
        protected void onProgressUpdate(String... text) {

            // Things to be done while execution of long running operation is in
            // progress. For example updating ProgessDialog
        }
    }
}
