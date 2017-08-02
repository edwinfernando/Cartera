package com.cartera.masterkey.cartera.views.dialogs;


import android.animation.Animator;
import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;

import com.cartera.masterkey.cartera.R;
import com.cartera.masterkey.cartera.util.Utilidades;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class FiltroClientesDialogFragment extends DialogFragment {

    public FiltroClientesDialogFragment() {
    }

    public static FiltroClientesDialogFragment newInstance(int centerX, int centerY) {
        FiltroClientesDialogFragment fragment = new FiltroClientesDialogFragment();
        Bundle args = new Bundle();
        args.putInt("cx", centerX);
        args.putInt("cy", centerY);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        setStyle(STYLE_NO_TITLE, R.style.AppTheme_NoActionBar);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_filtro_clientes_dialog, container, false);

        ButterKnife.bind(this, view);

        view.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop,
                                       int oldRight, int oldBottom) {
                v.removeOnLayoutChangeListener(this);
                int cx = getArguments().getInt("cx");
                int cy = getArguments().getInt("cy");

                int radius = (int) Math.hypot(right, bottom);

                Animator reveal = null;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    reveal = ViewAnimationUtils.createCircularReveal(v, cx, cy, 0, radius + 500);
                    reveal.setInterpolator(new DecelerateInterpolator(2f));
                    reveal.setDuration(1500);
                    reveal.start();
                }
            }
        });

        return view;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Dialog dialog = super.onCreateDialog(savedInstanceState);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        } else {
            dialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
                @Override
                public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
                    if (i == android.view.KeyEvent.KEYCODE_BACK) {
                        //Hide your keyboard here!!!
                        Utilidades.onDialogTouched(FiltroClientesDialogFragment.this, 1000, 20);
                        return true; // pretend we've processed it
                    }
                    return false;
                }
            });
        }

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        return dialog;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }

    @OnClick(R.id.cancelar_filtro)
    public void cancelarFiltro() {
        Utilidades.onDialogTouched(this, 1000, 20);
    }

    @OnClick(R.id.aplicar_filtro)
    public void aplicarFiltro() {
        Utilidades.onDialogTouched(this, 1000, 20);
    }
}
