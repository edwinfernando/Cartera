package com.cartera.masterkey.cartera.views.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.cartera.masterkey.cartera.R;
import com.cartera.masterkey.cartera.models.Cliente;
import com.cartera.masterkey.cartera.views.dialogs.ConfirmarPagoDialogFragment;
import com.cartera.masterkey.cartera.views.dialogs.FiltroClientesDialogFragment;
import com.cartera.masterkey.cartera.views.dialogs.PosponerPagoDialogFragment;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RealizarRecaudoFragment extends Fragment {

    private static final int POSPONER = 2;
    @Bind(R.id.txtCliente)
    TextView txtCliente;

    @Bind(R.id.txtCuentaNro)
    TextView txtCuentaNro;

    @Bind(R.id.edtValorCouta)
    EditText edtValorCouta;

    @Bind(R.id.edtCoutaNro)
    EditText edtCoutaNro;

    @Bind(R.id.txtFechaVencimiento)
    TextView txtFechaVencimiento;

    @Bind(R.id.txtSaldoAlPagar)
    TextView txtSaldoAlPagar;

    @Bind(R.id.txtFechaPago)
    TextView txtFechaPago;

    private Cliente cliente;

    public RealizarRecaudoFragment() {
        // Required empty public constructor
    }


    public static RealizarRecaudoFragment newInstance(Cliente cliente) {
        RealizarRecaudoFragment fragment = new RealizarRecaudoFragment();
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
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Recaudos");

        View view = inflater.inflate(R.layout.fragment_realizar_recaudo, container, false);
        ButterKnife.bind(this, view);

        txtCliente.setText(cliente.getCliente());
        txtCuentaNro.setText(cliente.getCuenta());

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
        inflater.inflate(R.menu.recaudo_cliente, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_ver_cliente:
                break;
            case R.id.action_posponer_pago:
                PosponerPagoDialogFragment posponerPagoDialogFragment = new PosponerPagoDialogFragment();
                posponerPagoDialogFragment.setTargetFragment(this, POSPONER);
                posponerPagoDialogFragment.show(getFragmentManager(), "posponerPagoDialog");

                Fragment frag = getFragmentManager().findFragmentByTag("posponerPagoDialog");

                if (frag != null) {
                    getFragmentManager().beginTransaction().remove(frag).commit();
                }
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.btnLimpiar)
    public void onclickBotonLimpiar(){}

    @OnClick(R.id.btnRecaudar)
    public void onclickBotonRecaudar(){
        ConfirmarPagoDialogFragment confirmarPagoDialogFragment = new ConfirmarPagoDialogFragment();
        confirmarPagoDialogFragment.setTargetFragment(this, POSPONER);
        confirmarPagoDialogFragment.show(getFragmentManager(), "confirmarPagoDialog");

        Fragment frag = getFragmentManager().findFragmentByTag("confirmarPagoDialog");

        if (frag != null) {
            getFragmentManager().beginTransaction().remove(frag).commit();
        }
    }
}
