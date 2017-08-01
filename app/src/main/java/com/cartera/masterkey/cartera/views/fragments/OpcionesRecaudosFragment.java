package com.cartera.masterkey.cartera.views.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cartera.masterkey.cartera.R;
import com.cartera.masterkey.cartera.views.activities.ClientesActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class OpcionesRecaudosFragment extends Fragment {

    public OpcionesRecaudosFragment() {
        // Required empty public constructor
    }

    public static OpcionesRecaudosFragment newInstance(String param1, String param2) {
        OpcionesRecaudosFragment fragment = new OpcionesRecaudosFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_opciones_recaudos, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @OnClick(R.id.bottonClientes)
    public void onclickBotonClientes(){
        gotoClientesActivity();
    }

    @OnClick(R.id.bottonPromesas)
    public void onclickBotonPromesas(){

    }

    @OnClick(R.id.bottonReportes)
    public void onclickBotonReportes(){

    }

    public void gotoClientesActivity(){
        Intent intent = new Intent(getActivity(), ClientesActivity.class);
        startActivity(intent);
    }
}
