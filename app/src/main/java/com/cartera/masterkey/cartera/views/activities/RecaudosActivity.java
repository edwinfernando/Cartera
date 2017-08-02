package com.cartera.masterkey.cartera.views.activities;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.cartera.masterkey.cartera.R;
import com.cartera.masterkey.cartera.views.fragments.OpcionesRecaudosFragment;
import com.cartera.masterkey.cartera.views.fragments.ProductosFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

public class RecaudosActivity extends AppCompatActivity implements FragmentManager.OnBackStackChangedListener, View.OnClickListener {

    @Bind(R.id.toolbar_title)
    TextView toolbar_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recaudos);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        ButterKnife.bind(this);

        toolbar_title.setText("Recaudos");

        toolbar.setNavigationOnClickListener(this);

        //Maneja la transicion entre fragments y crea una pila de fragment
        getSupportFragmentManager().addOnBackStackChangedListener(this);

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, new OpcionesRecaudosFragment());
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public void onClick(View view) {
        onBackPressed();
    }

    @Override
    public void onBackPressed() {
      //  int fragments = getSupportFragmentManager().getBackStackEntryCount();
       // if (fragments > 1) {
       //     if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP && fragments >= 3) {
                //  Utilidades.onFragmentTouched(getSupportFragmentManager().getFragments().get(fragments - 1), x, y);
      //      } else {
       //         getSupportFragmentManager().popBackStackImmediate();
     //       }
        //} else {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
      //  }
    }

    @Override
    public void onBackStackChanged() {

    }
}
