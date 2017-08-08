package com.cartera.masterkey.cartera.views.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.cartera.masterkey.cartera.R;
import com.cartera.masterkey.cartera.models.Cliente;
import com.cartera.masterkey.cartera.models.DatosCuentaCliente;
import com.cartera.masterkey.cartera.views.dialogs.DatosCuentaClienteDialogFragment;


public class DatosPersonalesClienteFragment extends Fragment {

    public DatosPersonalesClienteFragment() {
        // Required empty public constructor
    }

    public static DatosPersonalesClienteFragment newInstance(Cliente cliente) {
        DatosPersonalesClienteFragment fragment = new DatosPersonalesClienteFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("4040404");

        View view = inflater.inflate(R.layout.fragment_datos_personales_cliente, container, false);
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
        inflater.inflate(R.menu.cuenta_cliente, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_info_cuenta:
                DatosCuentaClienteDialogFragment filtroDialogFragment = DatosCuentaClienteDialogFragment.newInstance(new DatosCuentaCliente());
               // filtroDialogFragment.setTargetFragment(this, FILTRAR);
                filtroDialogFragment.show(getFragmentManager(), "filtrarDialog");

                Fragment frag = getFragmentManager().findFragmentByTag("filtrarDialog");

                if (frag != null) {
                    getFragmentManager().beginTransaction().remove(frag).commit();
                }
                break;
            case R.id.action_info_cliente:
                break;
            case R.id.action_gestiones:
                break;
            case R.id.action_agregar_cierre:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

}
