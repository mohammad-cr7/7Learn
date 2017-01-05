package com.example.sshahini.myapplication.view.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sshahini.myapplication.R;
import com.example.sshahini.myapplication.view.Util;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback, LocationListener {
    private GoogleMap googleMap;
    private Marker userMarker;
    private Marker destinationMarker;
    private Location userLocation;
    private Location destinationLocation;
    private TextView distanceTextView;
    private static final int REQUEST_CODE = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Util.setTypefaceToolbar(this,toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        SupportMapFragment supportMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        supportMapFragment.getMapAsync(this);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }


    private void setupLocationManager() {
        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        if (locationManager != null) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION
                            , Manifest.permission.ACCESS_COARSE_LOCATION}, REQUEST_CODE);
                }
                return;
            }
            if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                onLocationChanged(location);
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 4000, 5, this);
            }else if (locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)){
                Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                onLocationChanged(location);
                locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 4000, 5, this);
            }else if (locationManager.isProviderEnabled(LocationManager.PASSIVE_PROVIDER)){
                Location location = locationManager.getLastKnownLocation(LocationManager.PASSIVE_PROVIDER);
                onLocationChanged(location);
                locationManager.requestLocationUpdates(LocationManager.PASSIVE_PROVIDER, 4000, 5, this);
            }else {
                Toast.makeText(this,"لطفا موقعیت یاب خود را فعال کنید",Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public void onMapReady(final GoogleMap googleMap) {
        this.googleMap = googleMap;
        googleMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(LatLng latLng) {
                destinationLocation = new Location(LocationManager.GPS_PROVIDER);
                destinationLocation.setLatitude(latLng.latitude);
                destinationLocation.setLongitude(latLng.longitude);
                if (destinationMarker != null) {
                    destinationMarker.remove();
                } else {
                    distanceTextView = (TextView) findViewById(R.id.text_distance);
                    distanceTextView.setVisibility(View.VISIBLE);
                }
                distanceTextView.setText(getResources().getString(R.string.map_distance_label)
                        + String.valueOf(userLocation.distanceTo(destinationLocation) / 1000)
                        + getResources().getString(R.string.map_distance_metric_label));
                destinationMarker = googleMap.addMarker(new MarkerOptions().position(latLng).title("مقصد شما").icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_destination_marker)));
            }
        });
        setupLocationManager();
    }

    @Override
    public void onLocationChanged(Location location) {
        if (location != null && googleMap != null) {
            userLocation = location;
            LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
            if (userMarker != null) {
                userMarker.remove();
            }
            userMarker = googleMap.addMarker(new MarkerOptions().position(latLng).title("موقعیت شما").icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_user_marker)));
            CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, 10);
            googleMap.animateCamera(cameraUpdate);
        }
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == REQUEST_CODE) {
            if (grantResults[0] == 0) {
                setupLocationManager();
            }
        }
    }
}
