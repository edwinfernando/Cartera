package com.cartera.masterkey.cartera.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cartera.masterkey.cartera.R;
import com.cartera.masterkey.cartera.models.Coutas;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by edwinfernandomudelgado on 8/4/17.
 */

public class CoutasAdapter extends RecyclerView.Adapter<CoutasAdapter.CoutasHolder> {

    private Context context;
    private List<Coutas> dataResources;

    public CoutasAdapter(Context context, List<Coutas> dataResources) {
        this.context = context;
        this.dataResources = dataResources;
    }

    @Override
    public CoutasHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adaptador_coutas_cliente, parent, false);
        CoutasAdapter.CoutasHolder coutasHolder = new CoutasAdapter.CoutasHolder(view);
        return coutasHolder;
    }

    @Override
    public void onBindViewHolder(CoutasHolder holder, int position) {
        Coutas coutas = dataResources.get(position);
        holder.txtNCoutas.setText(String.valueOf(position));//coutas.getNroCuota()
        holder.txtFObligacion.setText(coutas.getFechaObligacion());
        holder.txtFPago.setText(coutas.getFechaPago());
        holder.txtCouta.setText(coutas.getCouta());
        holder.txtSaldoCouta.setText(coutas.getSaldoCouta());
        holder.txtSaldo.setText(coutas.getSaldo());
    }

    @Override
    public int getItemCount() {
        return dataResources.size();
    }

    public static class CoutasHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.txtNCoutas)
        TextView txtNCoutas;

        @Bind(R.id.txtFObligacion)
        TextView txtFObligacion;

        @Bind(R.id.txtFPago)
        TextView txtFPago;

        @Bind(R.id.txtCouta)
        TextView txtCouta;

        @Bind(R.id.txtSaldoCouta)
        TextView txtSaldoCouta;

        @Bind(R.id.txtSaldo)
        TextView txtSaldo;

        public CoutasHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
