package com.cartera.masterkey.cartera.views.dialogs;


import android.app.Dialog;
import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AppCompatSpinner;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;

import com.cartera.masterkey.cartera.R;
import com.cartera.masterkey.cartera.models.Impresora;
import com.cartera.masterkey.cartera.util.InformacionSession;
import com.cartera.masterkey.cartera.util.Utilidades;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import butterknife.Bind;
import butterknife.ButterKnife;
import service.BluetoothService;
import service.RunServicePrinter;


public class ConfigurarImpresoraDialogFragment extends DialogFragment {

    private Context context;
    private List<Impresora> list_printers;
    private BluetoothAdapter mBluetoothAdapter;
    private RunServicePrinter runServicePrinter;
    private ProgressDialog progress;
    private SharedPreferences prefer = null;
    private SharedPreferences.Editor editor;

    @Bind(R.id.spnImpresoras)
    AppCompatSpinner spnImpresora;

    public static ConfigurarImpresoraDialogFragment newInstance(Context context) {
        ConfigurarImpresoraDialogFragment dialogFragment = new ConfigurarImpresoraDialogFragment();
        dialogFragment.context = context;
        return dialogFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(getActivity().LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.fragment_configurar_impresora_dialog, null);

        builder.setView(layout);
        ButterKnife.bind(this, layout);

        setImpresoras();
        if(InformacionSession.getInstance().getImpresora()!=null) {
            spnImpresora.setSelection(indexOf(InformacionSession.getInstance().getImpresora().getAddress()));
        }

        builder.setTitle(R.string.configurarImpresora);
        prefer = getActivity().getSharedPreferences("impresora", Context.MODE_PRIVATE);

        if (BluetoothService.isRunning)
            builder.setPositiveButton(R.string.desconectar, null);
        else
            builder.setPositiveButton(R.string.sincronizar, null);

        builder.setNegativeButton(R.string.cancelar, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dismiss();
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
                        showLoading();
                        //  mAlertDialog.dismiss();

                        if (b.getText().toString().equals(getString(R.string.sincronizar))) {
                            sincronizarImpresora(mAlertDialog);
                        } else
                            desconectarImpresora(mAlertDialog);
                    }
                });
            }
        });
        mAlertDialog.show();

        return mAlertDialog;
    }

    public int indexOf(String mac) {
        for (int i = 0; i < list_printers.size(); i++)
            if (mac.equals(list_printers.get(i).getAddress()))
                return i;

        return -1;
    }

    private void setImpresoras() {
        if (!mBluetoothAdapter.isEnabled()) {
            Intent enableBluetooth = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBluetooth, 0);
        }
        list_printers = new ArrayList<>();

        Set<BluetoothDevice> pairedDevices = BluetoothAdapter.getDefaultAdapter().getBondedDevices();
        if (pairedDevices.size() > 0) {
            for (BluetoothDevice device : pairedDevices) {
                list_printers.add(new Impresora(device.getName(), device.getAddress()));
            }
        }
        ArrayAdapter<Impresora> adaptador = new ArrayAdapter<Impresora>(getActivity(), android.R.layout.simple_dropdown_item_1line, list_printers);

        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnImpresora.setAdapter(adaptador);
    }

    private void sincronizarImpresora(AlertDialog alertDialog) {
        Impresora print = (Impresora) spnImpresora.getSelectedItem();

        runServicePrinter = new RunServicePrinter(getActivity(), print.getAddress());
        try {
            if (BluetoothService.isRunning) {
                runServicePrinter.stop();
            }

            runServicePrinter.start();
            InformacionSession.getInstance().setImpresora(print);
            editor = prefer.edit();
            editor.putString("nombreImpresoraSeleccionada", print.getName());
            editor.putString("macImpresoraSeleccionada", print.getAddress());
            editor.commit();
            alertDialog.dismiss();
            hideLoading();
            // getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, getActivity().getIntent());
        } catch (Exception e) {
            e.printStackTrace();
            Utilidades.alertDialogInformacion(getActivity(), "Por favor apague y prenda nuevamente la impresora.");
            alertDialog.show();
            hideLoading();
        }
    }

    private void desconectarImpresora(AlertDialog alertDialog) {
        Impresora print = (Impresora) spnImpresora.getSelectedItem();

        runServicePrinter = new RunServicePrinter(getActivity(), print.getAddress());
        try {
            runServicePrinter.stop();
            alertDialog.show();
            Button b = alertDialog.getButton(AlertDialog.BUTTON_POSITIVE);
            b.setText(getString(R.string.sincronizar));
            hideLoading();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showLoading() {
        progress = ProgressDialog.show(context, null, getString(R.string.mensajeSincronizandoImpresora));
    }

    public void hideLoading() {
        progress.dismiss();
    }
}
