package com.cartera.masterkey.cartera.views.activities;

/**
 * Created by edwin on 31/07/2017.
 */

public interface ILoginView {
    void showLoading();
    void hideLoading();
    void onclickBotonStartSession();
    void gotoMainActivity();
    void showDialogLoginConfiguracion();
    void showErrorAutentication(String message);
    void showErrorLoginText(boolean error, int type);
    void showErrorPasswordText(boolean error, int type);
}
