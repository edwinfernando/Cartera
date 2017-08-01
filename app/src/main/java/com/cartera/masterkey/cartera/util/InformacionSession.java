package com.cartera.masterkey.cartera.util;

import com.cartera.masterkey.cartera.models.Impresora;

import java.util.List;


public final class InformacionSession {

    private static InformacionSession instance;

    private Impresora impresora;

    private InformacionSession() {}

    public static InformacionSession getInstance() {
        if (instance == null) {
            instance = new InformacionSession();
        }
        return instance;
    }

    public static void setInstance(InformacionSession instance) {
        InformacionSession.instance = instance;
    }


    public Impresora getImpresora() {
        return impresora;
    }

    public void setImpresora(Impresora impresora) {
        this.impresora = impresora;
    }
}