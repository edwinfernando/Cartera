package com.cartera.masterkey.cartera.views.dialogs;


import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.cartera.masterkey.cartera.R;
import com.cartera.masterkey.cartera.models.DatosCuentaCliente;

import butterknife.ButterKnife;

public class DatosCuentaClienteDialogFragment extends DialogFragment {

    public DatosCuentaClienteDialogFragment() {
        // Required empty public constructor
    }

    public static DatosCuentaClienteDialogFragment newInstance(DatosCuentaCliente datosCuentaCliente) {
        DatosCuentaClienteDialogFragment fragment = new DatosCuentaClienteDialogFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(getActivity().LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.fragment_datos_cuenta_cliente_dialog, null);

        builder.setView(layout);
        ButterKnife.bind(this, layout);

        builder.setTitle(R.string.detalle_de_la_cuenta);


        builder.setPositiveButton(R.string.aceptar, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        final AlertDialog mAlertDialog = builder.create();
        mAlertDialog.setOnShowListener(new DialogInterface.OnShowListener() {

            @Override
            public void onShow(DialogInterface dialog) {
                final Button b = mAlertDialog.getButton(AlertDialog.BUTTON_POSITIVE);
                b.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mAlertDialog.dismiss();
                    }
                });
            }
        });
        mAlertDialog.show();

        return mAlertDialog;
    }

}
