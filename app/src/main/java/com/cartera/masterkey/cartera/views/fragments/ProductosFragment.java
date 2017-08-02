package com.cartera.masterkey.cartera.views.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cartera.masterkey.cartera.R;
import com.cartera.masterkey.cartera.views.activities.RecaudosActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class ProductosFragment extends Fragment {
    public ProductosFragment() {
    }

    public static ProductosFragment newInstance() {
        ProductosFragment fragment = new ProductosFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_productos, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick(R.id.bottonRecaudos)
    public void onclickBotonRecaudos(){
        gotoFragmentOpcionesRecaudos();
    }

    public void gotoFragmentOpcionesRecaudos() {
        Intent intent = new Intent(getActivity(), RecaudosActivity.class);
        startActivity(intent);
    }
}
