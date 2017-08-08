package com.cartera.masterkey.cartera.models;

/**
 * Created by edwinfernandomudelgado on 8/4/17.
 */

public class Coutas {

    private String nroCuota;
    private String fechaObligacion;
    private String fechaPago;
    private String couta;
    private String saldoCouta;
    private String saldo;

    public String getNroCuota() {
        return nroCuota;
    }

    public void setNroCuota(String nroCuota) {
        this.nroCuota = nroCuota;
    }

    public String getFechaObligacion() {
        return fechaObligacion;
    }

    public void setFechaObligacion(String fechaObligacion) {
        this.fechaObligacion = fechaObligacion;
    }

    public String getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(String fechaPago) {
        this.fechaPago = fechaPago;
    }

    public String getCouta() {
        return couta;
    }

    public void setCouta(String couta) {
        this.couta = couta;
    }

    public String getSaldoCouta() {
        return saldoCouta;
    }

    public void setSaldoCouta(String saldoCouta) {
        this.saldoCouta = saldoCouta;
    }

    public String getSaldo() {
        return saldo;
    }

    public void setSaldo(String saldo) {
        this.saldo = saldo;
    }
}
