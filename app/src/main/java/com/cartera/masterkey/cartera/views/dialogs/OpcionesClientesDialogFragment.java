package com.cartera.masterkey.cartera.views.dialogs;


import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.cartera.masterkey.cartera.R;
import com.cartera.masterkey.cartera.models.Cliente;
import com.cartera.masterkey.cartera.views.fragments.InformacionCuotasClienteFragment;
import com.cartera.masterkey.cartera.views.fragments.RealizarRecaudoFragment;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OpcionesClientesDialogFragment extends DialogFragment {

    private Cliente cliente;

    public OpcionesClientesDialogFragment() {
        // Required empty public constructor
    }

    public static OpcionesClientesDialogFragment newInstance(Cliente cliente) {
        OpcionesClientesDialogFragment fragment = new OpcionesClientesDialogFragment();
        fragment.cliente = cliente;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NORMAL, R.style.CustomDialog);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getDialog().setTitle(R.string.opciones);
        View view = inflater.inflate(R.layout.fragment_opciones_clientes_dialog, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @OnClick(R.id.btnVerCliente)
    public void clickBotonVerCliente() {
        getDialog().dismiss();
        FragmentManager fragmentManager = getFragmentManager();
        InformacionCuotasClienteFragment informacionCuotasClienteFragment = InformacionCuotasClienteFragment.newInstance(cliente);

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        //fragmentTransaction.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right);
        // fragmentTransaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right);
        fragmentTransaction.replace(R.id.fragment_container, informacionCuotasClienteFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @OnClick(R.id.btnVerMapa)
    public void clickBotonVerMapa() {
        getDialog().dismiss();
    }

    @OnClick(R.id.btnRecaudar)
    public void clickBotonRecaudar() {
        getDialog().dismiss();
        FragmentManager fragmentManager = getFragmentManager();
        RealizarRecaudoFragment realizarRecaudoFragment = RealizarRecaudoFragment.newInstance(cliente);

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        //fragmentTransaction.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right);
        // fragmentTransaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right);
        fragmentTransaction.replace(R.id.fragment_container, realizarRecaudoFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}
