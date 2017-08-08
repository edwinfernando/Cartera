package com.cartera.masterkey.cartera.views.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.cartera.masterkey.cartera.R;
import com.cartera.masterkey.cartera.adapters.CoutasAdapter;
import com.cartera.masterkey.cartera.models.Cliente;
import com.cartera.masterkey.cartera.models.Coutas;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


public class InformacionCuotasClienteFragment extends Fragment {

    private Cliente cliente;
    private LinearLayoutManager linearLayoutManager;
    private List<Coutas> lCoutas;

    @Bind(R.id.list_coutas_cliente)
    RecyclerView list_coutas_cliente;

    public InformacionCuotasClienteFragment() {
        // Required empty public constructor
    }

    public static InformacionCuotasClienteFragment newInstance(Cliente cliente) {
        InformacionCuotasClienteFragment fragment = new InformacionCuotasClienteFragment();
        fragment.cliente = cliente;
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
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(cliente.getCliente());

        View view = inflater.inflate(R.layout.fragment_informacion_cuotas_cliente, container, false);

        ButterKnife.bind(this, view);
        linearLayoutManager = new LinearLayoutManager(getContext());

        Coutas coutas = new Coutas();
        coutas.setNroCuota("0");
        coutas.setFechaObligacion("16/06/2017");
        coutas.setFechaPago("");
        coutas.setCouta("0.00");
        coutas.setSaldoCouta("75.000,00");
        coutas.setSaldo("0.00");

        lCoutas = new ArrayList<>();
        lCoutas.add(coutas);
        lCoutas.add(coutas);
        lCoutas.add(coutas);

        setAdapterCoutas(lCoutas);

        return view;
    }

    private void setAdapterCoutas(List<Coutas> lCoutas) {
        CoutasAdapter adapter = new CoutasAdapter(getContext(), lCoutas);
        list_coutas_cliente.setAdapter(adapter);
        list_coutas_cliente.setLayoutManager(linearLayoutManager);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
        inflater.inflate(R.menu.coutas_cliente, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_ver_cliente:
                FragmentManager fragmentManager = getFragmentManager();
                DatosPersonalesClienteFragment datosClienteFragment = DatosPersonalesClienteFragment.newInstance(cliente);

                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                //fragmentTransaction.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right);
                // fragmentTransaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right);
                fragmentTransaction.replace(R.id.fragment_container, datosClienteFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
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
