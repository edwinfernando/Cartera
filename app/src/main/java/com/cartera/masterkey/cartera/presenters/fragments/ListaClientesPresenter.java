package com.cartera.masterkey.cartera.presenters.fragments;

import com.cartera.masterkey.cartera.models.Cliente;
import com.cartera.masterkey.cartera.views.fragments.IListaClientesView;

/**
 * Created by edwin on 1/08/2017.
 */

public class ListaClientesPresenter implements IListaClientesPresenter {

    IListaClientesView view;

    public ListaClientesPresenter(IListaClientesView view) {
        this.view = view;
    }

    @Override
    public void showDialogOpcionesClientes(Cliente cliente) {
        view.showDialogOpcionesClientes(cliente);
    }
}
