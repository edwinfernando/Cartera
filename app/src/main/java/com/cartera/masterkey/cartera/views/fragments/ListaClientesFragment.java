package com.cartera.masterkey.cartera.views.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.cartera.masterkey.cartera.R;
import com.cartera.masterkey.cartera.adapters.ClientesAdapter;
import com.cartera.masterkey.cartera.models.Cliente;
import com.cartera.masterkey.cartera.presenters.fragments.IListaClientesPresenter;
import com.cartera.masterkey.cartera.presenters.fragments.ListaClientesPresenter;
import com.cartera.masterkey.cartera.views.dialogs.FiltroClientesDialogFragment;
import com.cartera.masterkey.cartera.views.dialogs.OpcionesClientesDialogFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ListaClientesFragment extends Fragment implements SearchView.OnQueryTextListener, IListaClientesView {

    private static final int FILTRAR = 1;
    private IListaClientesPresenter listaClientesPresenter;
    private LinearLayoutManager linearLayoutManager;
    private List<Cliente> lClientes;

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

        View view = inflater.inflate(R.layout.fragment_lista_clientes, container, false);

        ButterKnife.bind(this, view);

        linearLayoutManager = new LinearLayoutManager(getContext());

        Cliente clientes = new Cliente();
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

        listaClientesPresenter = new ListaClientesPresenter(this);

        setAdapterClientes(lClientes);

        return view;
    }

    private void setAdapterClientes(List<Cliente> lClientes) {
        ClientesAdapter adapter = new ClientesAdapter(getContext(), lClientes, listaClientesPresenter);
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
                searchView.setOnQueryTextListener(this);
                break;
            case R.id.action_ver_mapa:
                break;
            case R.id.action_filtar:
                FiltroClientesDialogFragment filtroDialogFragment = FiltroClientesDialogFragment.newInstance(1000, -100);
                filtroDialogFragment.setTargetFragment(this, FILTRAR);
                filtroDialogFragment.show(getFragmentManager(), "filtrarDialog");

                Fragment frag = getFragmentManager().findFragmentByTag("filtrarDialog");

                if (frag != null) {
                    getFragmentManager().beginTransaction().remove(frag).commit();
                }
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

    @Override
    public void showDialogOpcionesClientes(Cliente cliente) {
        OpcionesClientesDialogFragment dialogFragment = OpcionesClientesDialogFragment.newInstance(cliente);
        //dialogFragment.setTargetFragment(this, CALLBACK_ADAPTER);
        dialogFragment.show(getFragmentManager(), "opcionesDialog");

        Fragment frag = getFragmentManager().findFragmentByTag("opcionesDialog");

        if (frag != null) {
            getFragmentManager().beginTransaction().remove(frag).commit();
        }
    }
}
