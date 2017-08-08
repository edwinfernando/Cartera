package com.cartera.masterkey.cartera.views.fragments;

import android.Manifest;
import android.content.Context;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.cartera.masterkey.cartera.R;
import com.cartera.masterkey.cartera.models.Marker;
import com.cartera.masterkey.cartera.presenters.fragments.IMapsPresenter;
import com.cartera.masterkey.cartera.presenters.fragments.MapsPresenter;
import com.cartera.masterkey.cartera.views.activities.CheckPermissionActivityManager;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsFragment extends FragmentActivity implements IMapsView, OnMapReadyCallback {

    private GoogleMap mMap;
    private IMapsPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        presenter = new MapsPresenter(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.onStop();
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
        //mMap.getUiSettings().setMyLocationButtonEnabled(true);
        if (CheckPermissionActivityManager.checkPermission(this, Manifest.permission.ACCESS_FINE_LOCATION, null)) {
            mMap.setMyLocationEnabled(true);
        }

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }

    @Override
    public Context getContext() {
        return this;
    }

    //refreca la ubicacion en el mapa
    @Override
    public void refreshMap(Marker marker) {
        mMap.clear();
        LatLng location = new LatLng(marker.getLat(), marker.getLng());
        mMap.addMarker(new MarkerOptions().position(location).title(marker.getName()));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 15.0f));//zoom
    }
}