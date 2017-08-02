package com.cartera.masterkey.cartera.models;

/**
 * Created by edwin on 31/07/2017.
 */

public class Cliente {

    private String empresa;
    private String cuenta;
    private String cliente;
    private String saldo;
    private String grupo;
    private String sVencida;
    private String dMora;

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getCuenta() {
        return cuenta;
    }

    public void setCuenta(String cuenta) {
        this.cuenta = cuenta;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getSaldo() {
        return saldo;
    }

    public void setSaldo(String saldo) {
        this.saldo = saldo;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    public String getsVencida() {
        return sVencida;
    }

    public void setsVencida(String sVencida) {
        this.sVencida = sVencida;
    }

    public String getdMora() {
        return dMora;
    }

    public void setdMora(String dMora) {
        this.dMora = dMora;
    }
}
