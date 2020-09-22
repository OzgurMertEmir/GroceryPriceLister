package com.example.grocerypricelister;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class Maps extends FragmentActivity implements OnMapReadyCallback {
    //

    private GoogleMap mMap;
    LocationManager locationManager;
    LocationListener locationListener;
    ControllerMaster controllerMaster;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map_layout);


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        MapFragment mapFragment1 = (MapFragment)getFragmentManager().findFragmentById(R.id.mapFragment);
        mapFragment1.getMapAsync(this);
        controllerMaster = new ControllerMaster();
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
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
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

        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        }
        else
        {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
        }
        // Add a marker in Kızılay and move the camera

        if( controllerMaster.getMarketManager().getChosenMarket() == null )
        {
            getLatLngFromDatabase();
        }
        else {
            try {
                MarkerOptions options = new MarkerOptions();
                options.position(controllerMaster.getMarketManager().getChosenMarketLocation());
                mMap.addMarker(options);
                controllerMaster.getMarketManager().removeChosenMarket();
            }
            catch (Exception e){
                e.printStackTrace();
            }

        }
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(39.902397, 32.860538), 10));
        if(Manifest.permission.ACCESS_FINE_LOCATION.equals("true")){
            mMap.setMyLocationEnabled(true);
        }
        else{
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        }
        mMap.getUiSettings().setMyLocationButtonEnabled(true);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(grantResults.length > 0)
        {
            if(requestCode == 1)
            {
                if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)
                {
                    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 50, locationListener);
                }
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    public void openMain(View view)
    {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }



    public void getLatLngFromDatabase() {
            for (Market market : controllerMaster.getMarkets()) {
                LatLng latLng = new LatLng(market.getLatitude(), market.getLongtitude());
                MarkerOptions options = new MarkerOptions();
                options.position(latLng);
                options.title(market.getName());
                if (market.isOpen()) {
                    options.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
                } else {
                    options.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
                }
                mMap.addMarker(options);
                System.out.println("------------------------------------------LATLNG ADDED TO MARKER POINTS --------------------------------------------------------------");
            }
    }
}
