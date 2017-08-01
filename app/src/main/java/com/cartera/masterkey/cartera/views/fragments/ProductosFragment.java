package com.cartera.masterkey.cartera.views.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cartera.masterkey.cartera.R;

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
        FragmentManager fragmentManager = getFragmentManager();
        OpcionesRecaudosFragment fragment = new OpcionesRecaudosFragment();

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        //fragmentTransaction.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right);
       // fragmentTransaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right);
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}
