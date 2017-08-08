package com.cartera.masterkey.cartera.views.activities;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.cartera.masterkey.cartera.R;
import com.cartera.masterkey.cartera.util.Utilidades;
import com.cartera.masterkey.cartera.views.fragments.ListaClientesFragment;
import com.cartera.masterkey.cartera.views.fragments.ProductosFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ClientesActivity extends AppCompatActivity implements FragmentManager.OnBackStackChangedListener, View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clientes);
        ButterKnife.bind(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Cliente");
        setSupportActionBar(toolbar);

        toolbar.setNavigationOnClickListener(this);

        //Maneja la transicion entre fragments y crea una pila de fragment
        getSupportFragmentManager().addOnBackStackChangedListener(this);

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, new ListaClientesFragment());
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public void onClick(View view) {
        onBackPressed();
    }

    @Override
    public void onBackPressed() {
        int fragments = getSupportFragmentManager().getBackStackEntryCount();
        if (fragments > 1) {
           // if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP && fragments >= 3) {
              //  Utilidades.onFragmentTouched(getSupportFragmentManager().getFragments().get(fragments - 1), x, y);
           // } else {
                getSupportFragmentManager().popBackStackImmediate();
           // }
        } else {
            Intent intent = new Intent(this, RecaudosActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public void onBackStackChanged() {

    }
}
