package com.cartera.masterkey.cartera.presenters.fragments;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.Location;

import com.cartera.masterkey.cartera.models.Marker;
import com.cartera.masterkey.cartera.util.LocationChangeListener;
import com.cartera.masterkey.cartera.util.MyLocationListener;
import com.cartera.masterkey.cartera.views.fragments.IMapsView;
import com.google.gson.Gson;

/**
 * Created by krlosf on 29/08/16.
 */
public class MapsPresenter implements IMapsPresenter, LocationChangeListener {
    public static final String ACTION_REFRESH_MAP = "co.gdgcali.notifications.REFRESH_MAP";
    private IMapsView view;
    private BroadcastReceiver brRefreshMap;
    private IntentFilter intentFilter;
    private MyLocationListener myLocationListener;

    public MapsPresenter(final IMapsView view) {
        this.view = view;

        brRefreshMap = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String jsonMarker = intent.getStringExtra("marker");
                Gson gson = new Gson();
                Marker marker = gson.fromJson(jsonMarker, Marker.class);
                view.refreshMap(marker);

                //Utilities.showNotification(view.getContext(), 123, R.mipmap.ic_launcher,
               //         "Nueva notificacion", "Mapa centrado en: "+marker.getName(), null);
            }
        };

        intentFilter = new IntentFilter();
        intentFilter.addAction(ACTION_REFRESH_MAP);

        myLocationListener = new MyLocationListener(view.getContext(), this);
    }

    @Override
    public void onResume() {
        view.getContext().registerReceiver(brRefreshMap, intentFilter);
        myLocationListener.start();
    }

    @Override
    public void onStop() {
        view.getContext().unregisterReceiver(brRefreshMap);

        myLocationListener.stop();
    }

    @Override
    public void onLocationChange(Location location) {
        Marker marker = new Marker("Estoy aqui", location.getLatitude(), location.getLongitude());
        view.refreshMap(marker);
    }
}
