package com.cartera.masterkey.cartera.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cartera.masterkey.cartera.R;
import com.cartera.masterkey.cartera.models.Cliente;
import com.cartera.masterkey.cartera.presenters.fragments.IListaClientesPresenter;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by edwin on 31/07/2017.
 */

public class ClientesAdapter extends RecyclerView.Adapter<ClientesAdapter.ClientesHolder>  {
    private Context context;
    private List<Cliente> dataResources;
    private IListaClientesPresenter presenter;

    public ClientesAdapter(Context context, List<Cliente> dataResources, IListaClientesPresenter presenter) {
        this.context = context;
        this.dataResources = dataResources;
        this.presenter = presenter;
    }

    @Override
    public ClientesHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adaptador_clientes,parent,false);
        ClientesAdapter.ClientesHolder clientesHolder = new ClientesAdapter.ClientesHolder(view);
        return clientesHolder;
    }

    @Override
    public void onBindViewHolder(ClientesHolder holder, int position) {
        final Cliente clientes = dataResources.get(position);
        holder.txtEmpresa.setText(clientes.getEmpresa());
        holder.txtCuenta.setText(clientes.getCuenta());
        holder.txtCliente.setText(clientes.getCliente());
        holder.txtSaldo.setText(clientes.getSaldo());
        holder.txtGrupo.setText(clientes.getGrupo());
        holder.txtSVencida.setText(clientes.getsVencida());
        holder.txtDMora.setText(clientes.getdMora());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.showDialogOpcionesClientes(clientes);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataResources.size();
    }

    public static class ClientesHolder extends RecyclerView.ViewHolder{

        @Bind(R.id.txtEmpresa)
        TextView txtEmpresa;

        @Bind(R.id.txtCuenta)
        TextView txtCuenta;

        @Bind(R.id.txtCliente)
        TextView txtCliente;

        @Bind(R.id.txtSaldo)
        TextView txtSaldo;

        @Bind(R.id.txtGrupo)
        TextView txtGrupo;

        @Bind(R.id.txtSVencida)
        TextView txtSVencida;

        @Bind(R.id.txtDMora)
        TextView txtDMora;

        public ClientesHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
