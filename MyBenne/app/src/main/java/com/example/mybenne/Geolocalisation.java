package com.example.mybenne;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class Geolocalisation extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_geolocalisation);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
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

        // Add a marker in Sydney and move the camera
        LatLng BenneMairie = new LatLng(49.847177, 3.287543);
        LatLng BenneHotelMemorial = new LatLng(49.846054, 3.286342);
        LatLng BenneGoldenPub = new LatLng(49.847342, 3.287045);
        LatLng BenneBoulanderiePaul = new LatLng(49.845640, 3.290009);
        LatLng BenneEcoleLyon = new LatLng(49.845280, 3.284628);
        mMap.addMarker(new MarkerOptions().position(BenneMairie).title("Benne de la Mairie"));
        mMap.addMarker(new MarkerOptions().position(BenneHotelMemorial).title("Benne de l'Hotel Memorial"));
        mMap.addMarker(new MarkerOptions().position(BenneGoldenPub).title("Benne du Golden Pub"));
        mMap.addMarker(new MarkerOptions().position(BenneBoulanderiePaul).title("Benne de la boulangerie Paul"));
        mMap.addMarker(new MarkerOptions().position(BenneEcoleLyon).title("Benne de l'Ecole Lyon"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(BenneMairie));
    }
}
