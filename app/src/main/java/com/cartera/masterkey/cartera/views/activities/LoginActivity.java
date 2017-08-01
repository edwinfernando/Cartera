package com.cartera.masterkey.cartera.views.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.cartera.masterkey.cartera.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity implements ILoginView{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @OnClick(R.id.btnStartSession)
    public void onclickBotonStartSession(){
        gotoMainActivity();
    }

    @Override
    public void gotoMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void showDialogLoginConfiguracion() {

    }

    @Override
    public void showErrorAutentication(String message) {

    }

    @Override
    public void showErrorLoginText(boolean error, int type) {

    }

    @Override
    public void showErrorPasswordText(boolean error, int type) {

    }


}
