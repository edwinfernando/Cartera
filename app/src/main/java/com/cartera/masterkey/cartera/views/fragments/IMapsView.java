package com.cartera.masterkey.cartera.views.fragments;

import android.content.Context;

import com.cartera.masterkey.cartera.models.Marker;


public interface IMapsView {
    Context getContext();

    void refreshMap(Marker marker);
}
