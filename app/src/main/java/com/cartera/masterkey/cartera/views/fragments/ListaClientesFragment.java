package com.cartera.masterkey.cartera.views.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;

import com.cartera.masterkey.cartera.R;
import com.cartera.masterkey.cartera.adapters.ClientesAdapter;
import com.cartera.masterkey.cartera.models.Clientes;
import com.cartera.masterkey.cartera.util.Utilidades;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ListaClientesFragment extends Fragment implements SearchView.OnQueryTextListener {

    private LinearLayoutManager linearLayoutManager;
    private List<Clientes> lClientes;

    @Bind(R.id.list_clientes)
    RecyclerView list_clientes;

    public ListaClientesFragment() {
        // Required empty public constructor
    }

    public static ListaClientesFragment newInstance() {
        ListaClientesFragment fragment = new ListaClientesFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);
      //  ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Clientes");

        View view = inflater.inflate(R.layout.fragment_lista_clientes, container, false);

        ButterKnife.bind(this, view);

        linearLayoutManager = new LinearLayoutManager(getContext());

        Clientes clientes = new Clientes();
        clientes.setEmpresa("Master Key");
        clientes.setCuenta("40257");
        clientes.setCliente("Yolanda Vallego");
        clientes.setSaldo("300000");
        clientes.setGrupo("Mora 60");
        clientes.setsVencida("225000");
        clientes.setdMora("90");

        lClientes = new ArrayList<>();
        lClientes.add(clientes);
        lClientes.add(clientes);


        setAdapterClientes(lClientes);

        return view;
    }

    private void setAdapterClientes(List<Clientes> lClientes) {
        ClientesAdapter adapter = new ClientesAdapter(getContext(), lClientes);
        list_clientes.setAdapter(adapter);
        list_clientes.setLayoutManager(linearLayoutManager);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
        inflater.inflate(R.menu.clientes_mapa, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.action_search:
                SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);
                //permite modificar el hint que el EditText muestra por defecto
                //searchView.setQueryHint(getText(R.string.search));
                searchView.setOnQueryTextListener(this);
                break;
            case R.id.action_ver_mapa:

                break;
            case R.id.action_filtar:

                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }
}
